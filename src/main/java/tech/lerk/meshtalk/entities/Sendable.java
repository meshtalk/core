package tech.lerk.meshtalk.entities;

import java.sql.Time;
import java.util.UUID;

public abstract class Sendable<I extends Sendable> implements Comparable<I> {
    private UUID id;
    private UUID sender;
    private UUID receiver;
    private Time date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    public int compareTo(I o) {
        return getDate().compareTo(o.getDate());
    }
}
