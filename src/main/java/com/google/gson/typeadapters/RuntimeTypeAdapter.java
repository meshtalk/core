package com.google.gson.typeadapters;

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

public class RuntimeTypeAdapter<R> extends TypeAdapter<R> {
    private final boolean maintainType;
    private final String typeFieldName;
    private final Class<?> baseType;
    private final Map<String, TypeAdapter<?>> labelToDelegate;
    private final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate;
    private final Map<Class<?>, String> subtypeToLabel;

    public RuntimeTypeAdapter(boolean maintainType,
                              String typeFieldName,
                              Class<?> baseType,
                              Map<String, TypeAdapter<?>> labelToDelegate,
                              Map<Class<?>, TypeAdapter<?>> subtypeToDelegate,
                              Map<Class<?>, String> subtypeToLabel) {
        this.maintainType = maintainType;
        this.typeFieldName = typeFieldName;
        this.baseType = baseType;
        this.labelToDelegate = labelToDelegate;
        this.subtypeToDelegate = subtypeToDelegate;
        this.subtypeToLabel = subtypeToLabel;
    }

    @Override
    public R read(JsonReader in) throws IOException {
        JsonElement jsonElement = Streams.parse(in);
        JsonElement labelJsonElement;
        if (maintainType) {
            labelJsonElement = jsonElement.getAsJsonObject().get(typeFieldName);
        } else {
            labelJsonElement = jsonElement.getAsJsonObject().remove(typeFieldName);
        }

        if (labelJsonElement == null) {
            throw new JsonParseException("cannot deserialize " + baseType
                    + " because it does not define a field named " + typeFieldName);
        }
        String label = labelJsonElement.getAsString();
        @SuppressWarnings("unchecked") // registration requires that subtype extends T
                TypeAdapter<R> delegate = (TypeAdapter<R>) labelToDelegate.get(label);
        if (delegate == null) {
            throw new JsonParseException("cannot deserialize " + baseType + " subtype named "
                    + label + "; did you forget to register a subtype?");
        }
        return delegate.fromJsonTree(jsonElement);
    }

    @Override
    public void write(JsonWriter out, R value) throws IOException {
        Class<?> srcType = value.getClass();
        String label = subtypeToLabel.get(srcType);
        @SuppressWarnings("unchecked") // registration requires that subtype extends T
                TypeAdapter<R> delegate = (TypeAdapter<R>) subtypeToDelegate.get(srcType);
        if (delegate == null) {
            throw new JsonParseException("cannot serialize " + srcType.getName()
                    + "; did you forget to register a subtype?");
        }
        JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();

        if (maintainType) {
            Streams.write(jsonObject, out);
            return;
        }

        JsonObject clone = new JsonObject();

        if (jsonObject.has(typeFieldName)) {
            throw new JsonParseException("cannot serialize " + srcType.getName()
                    + " because it already defines a field named " + typeFieldName);
        }
        clone.add(typeFieldName, new JsonPrimitive(label));

        for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
            clone.add(e.getKey(), e.getValue());
        }
        Streams.write(clone, out);
    }
}
