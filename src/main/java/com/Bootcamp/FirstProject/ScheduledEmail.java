package com.Bootcamp.FirstProject;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



public class ScheduledEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String receiver;
    private String sender;
    private String body;
    private String subject;
    private Date time;

    public ScheduledEmail(int id,String receiver, String sender, String body, String subject, Date time) {
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
        this.body = body;
        this.subject = subject;
        this.time = time;
    }

    public ScheduledEmail(String receiver, String sender, String body, String subject, Date time) {
        this.receiver = receiver;
        this.sender = sender;
        this.body = body;
        this.subject = subject;
        this.time = time;
    }

    public ScheduledEmail() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ScheduledEmail{" +
                "id='" + id + '\'' +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                ", time=" + time +
                '}';
    }
}
