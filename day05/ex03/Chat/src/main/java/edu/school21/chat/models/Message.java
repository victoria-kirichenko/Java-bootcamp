package edu.school21.chat.models;

import java.util.Objects;
public class Message {
    private int id;
    private ChatRoom chatName;
    private User author;
    private String messageText;
    private String timeDate;

    public Message(int id, ChatRoom chatName, User author, String messageText, String timeDate) {
        this.id = id;
        this.chatName = chatName;
        this.author = author;
        this.messageText = messageText;
        this.timeDate = timeDate;
    }

    public int getId() {
        return id;
    }

    public ChatRoom getChatName() {
        return chatName;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChatName(ChatRoom chatName) {
        this.chatName = chatName;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(chatName, message.chatName) && Objects.equals(author, message.author) && Objects.equals(messageText, message.messageText) && Objects.equals(timeDate, message.timeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatName, author, messageText, timeDate);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chatName=" + chatName +
                ", author=" + author +
                ", messageText='" + messageText + '\'' +
                ", timeDate='" + timeDate + '\'' +
                '}';
    }
}