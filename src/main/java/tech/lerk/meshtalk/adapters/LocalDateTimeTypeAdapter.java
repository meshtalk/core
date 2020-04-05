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
        Integer year = jsonDeserializationContext.deserialize(jsonObject.get(YEAR), Integer.class);
        Integer month = jsonDeserializationContext.deserialize(jsonObject.get(MONTH), Integer.class);
        Integer day = jsonDeserializationContext.deserialize(jsonObject.get(DAY), Integer.class);
        Integer hour = jsonDeserializationContext.deserialize(jsonObject.get(HOUR), Integer.class);
        Integer minute = jsonDeserializationContext.deserialize(jsonObject.get(MINUTE), Integer.class);
        Integer second = jsonDeserializationContext.deserialize(jsonObject.get(SECOND), Integer.class);
        return LocalDateTime.of(year, month, day, hour, minute, second);
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
