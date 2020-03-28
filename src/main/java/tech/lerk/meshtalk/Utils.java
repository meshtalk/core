package tech.lerk.meshtalk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import tech.lerk.meshtalk.adapters.PrivateKeyTypeAdapter;
import tech.lerk.meshtalk.adapters.PublicKeyTypeAdapter;
import tech.lerk.meshtalk.entities.Chat;
import tech.lerk.meshtalk.entities.Message;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Utils {
    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(PrivateKey.class, new PrivateKeyTypeAdapter())
                .registerTypeAdapter(PublicKey.class, new PublicKeyTypeAdapter())
                .registerTypeAdapter(Message.class, RuntimeTypeAdapterFactory.of(Message.class, "type")
                        .registerSubtype(Message.class, Message.class.getName())
                        .registerSubtype(Chat.Handshake.class, Chat.Handshake.class.getName()))
                .create();
    }
}
