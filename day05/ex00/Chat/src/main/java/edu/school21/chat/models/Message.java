package edu.school21.chat.models;

import java.util.Objects;
public class Message {
    private int id;
    private int chatId;
    private int author;
    private String messageText;
    private String timeDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && chatId == message.chatId && author == message.author && Objects.equals(messageText, message.messageText) && Objects.equals(timeDate, message.timeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, author, messageText, timeDate);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", author=" + author +
                ", messageText='" + messageText + '\'' +
                ", timeDate='" + timeDate + '\'' +
                '}';
    }
}