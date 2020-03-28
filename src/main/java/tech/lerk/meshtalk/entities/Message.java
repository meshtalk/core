package tech.lerk.meshtalk.entities;

import java.sql.Time;
import java.util.UUID;

public class Message implements Sendable<Message> {
    private UUID id;
    private UUID chat;
    private UUID sender;
    private UUID receiver;
    private String content;
    private Time date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getChat() {
        return chat;
    }

    public void setChat(UUID chat) {
        this.chat = chat;
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public void setReceiver(UUID receiver) {
        this.receiver = receiver;
    }

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    @Override
    public int compareTo(Message o) {
        return getDate().compareTo(o.getDate());
    }
}
