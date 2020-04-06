package tech.lerk.meshtalk.entities;

import java.util.HashMap;
import java.util.UUID;

public class Chat implements Comparable<Chat> {
    private UUID id;
    private String title;
    private UUID recipient;
    private UUID sender;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getRecipient() {
        return recipient;
    }

    public void setRecipient(UUID recipient) {
        this.recipient = recipient;
    }

    @Override
    public int compareTo(Chat o) {
        return getId().compareTo(o.getId());
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }
}
