package tech.lerk.meshtalk.entities;

public class Handshake extends Sendable {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}