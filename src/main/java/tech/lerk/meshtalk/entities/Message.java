package tech.lerk.meshtalk.entities;

public class Message extends Sendable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
