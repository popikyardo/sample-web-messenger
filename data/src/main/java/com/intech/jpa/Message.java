package com.intech.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.intech.views.View;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by popikyardo on 22.07.15.
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({View.MessageDataTable.class})
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "from_user_id", referencedColumnName = "id")
    @JsonView({View.MessageDataTable.class})
    private User fromUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "to_user_id", referencedColumnName = "id")
    @JsonView({View.MessageDataTable.class})
    private User toUser;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(name = "topic")
    @JsonView({View.MessageDataTable.class})
    private String topic;

    @Column(name = "message")
    @JsonView({View.MessageDataTable.class})
    private String message;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView({View.MessageDataTable.class})
    private Date date = new Date();

    @Column(name = "is_delete")
    private boolean deleted = false;

    public Message(){

    }

    public Message(User fromUser, User toUser, String message){
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
        this.type = MessageType.SYSTEM;
        this.status = MessageStatus.NEW;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public enum MessageStatus{
        NEW,
        READ,
        DELETED
    }

    public enum MessageType {
        SYSTEM
    }

}
