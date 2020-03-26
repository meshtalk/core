package tech.lerk.meshtalk.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PublicKeyTypeAdapter implements JsonSerializer<PublicKey>, JsonDeserializer<PublicKey> {
    private static final String DATA = "DATA";
    private static final String TAG = PublicKeyTypeAdapter.class.getCanonicalName();

    public PublicKey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String deserialized = jsonDeserializationContext.deserialize(jsonObject.get(DATA), String.class);
        byte[] decodedKey = Base64.getMimeDecoder().decode(deserialized);
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JsonParseException("Unable to decode key!", e);
        }
    }

    public JsonElement serialize(PublicKey privateKey, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        String encodedKey = Base64.getMimeEncoder().encodeToString(privateKey.getEncoded());
        jsonObject.add(DATA, jsonSerializationContext.serialize(encodedKey));
        return jsonObject;
    }
}
