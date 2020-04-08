package tech.lerk.meshtalk.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private static final String YEAR = "YEAR";
    private static final String MONTH = "MONTH";
    private static final String DAY = "DAY";
    private static final String HOUR = "HOUR";
    private static final String MINUTE = "MINUTE";
    private static final String SECOND = "SECOND";

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return LocalDateTime.of(deserializeInt(jsonDeserializationContext, jsonObject, YEAR),
                deserializeInt(jsonDeserializationContext, jsonObject, MONTH),
                deserializeInt(jsonDeserializationContext, jsonObject, DAY),
                deserializeInt(jsonDeserializationContext, jsonObject, HOUR),
                deserializeInt(jsonDeserializationContext, jsonObject, MINUTE),
                deserializeInt(jsonDeserializationContext, jsonObject, SECOND));
    }

    private Integer deserializeInt(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject, String year) {
        return jsonDeserializationContext.deserialize(jsonObject.get(year), Integer.class);
    }

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(YEAR, jsonSerializationContext.serialize(localDateTime.getYear(), Integer.class));
        jsonObject.add(MONTH, jsonSerializationContext.serialize(localDateTime.getMonthValue(), Integer.class));
        jsonObject.add(DAY, jsonSerializationContext.serialize(localDateTime.getDayOfMonth(), Integer.class));
        jsonObject.add(HOUR, jsonSerializationContext.serialize(localDateTime.getHour(), Integer.class));
        jsonObject.add(MINUTE, jsonSerializationContext.serialize(localDateTime.getMinute(), Integer.class));
        jsonObject.add(SECOND, jsonSerializationContext.serialize(localDateTime.getSecond(), Integer.class));
        return jsonObject;
    }
}
