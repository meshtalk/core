package tech.lerk.meshtalk.entities;

import java.util.UUID;

public class Handshake extends Sendable {
    private String key;
    private String iv;
    private UUID reply;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public UUID getReply() {
        return reply;
    }

    public void setReply(UUID reply) {
        this.reply = reply;
    }
}