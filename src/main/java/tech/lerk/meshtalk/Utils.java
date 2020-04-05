package tech.lerk.meshtalk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import tech.lerk.meshtalk.adapters.LocalDateTimeTypeAdapter;
import tech.lerk.meshtalk.adapters.PrivateKeyTypeAdapter;
import tech.lerk.meshtalk.adapters.PublicKeyTypeAdapter;
import tech.lerk.meshtalk.entities.Handshake;
import tech.lerk.meshtalk.entities.Message;
import tech.lerk.meshtalk.entities.Sendable;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;

public class Utils {
    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(PrivateKey.class, new PrivateKeyTypeAdapter())
                .registerTypeAdapter(PublicKey.class, new PublicKeyTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Sendable.class, "type")
                        .registerSubtype(Message.class, Message.class.getName())
                        .registerSubtype(Handshake.class, Handshake.class.getName()))
                .create();
    }
}
