package tech.lerk.meshtalk.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivateKeyTypeAdapter implements JsonSerializer<PrivateKey>, JsonDeserializer<PrivateKey> {
    private static final String DATA = "DATA";

    public PrivateKey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String deserialized = jsonDeserializationContext.deserialize(jsonObject.get(DATA), String.class);
        byte[] decodedKey = Base64.getMimeDecoder().decode(deserialized);
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JsonParseException("Unable to decode key!", e);
        }
    }

    public JsonElement serialize(PrivateKey privateKey, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        String encodedKey = Base64.getMimeEncoder().encodeToString(privateKey.getEncoded());
        jsonObject.add(DATA, jsonSerializationContext.serialize(encodedKey));
        return jsonObject;
    }
}

