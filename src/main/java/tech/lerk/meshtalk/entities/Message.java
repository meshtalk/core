package tech.lerk.meshtalk.entities;

import java.util.UUID;

public class Message extends Sendable<Message> {
    private UUID chat;
    private String content;

    public UUID getChat() {
        return chat;
    }

    public void setChat(UUID chat) {
        this.chat = chat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
