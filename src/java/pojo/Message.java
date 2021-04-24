/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Alfonso Felix
 */
public class Message {
    @Expose
    private String token;
    @Expose
    private String to;
    
    @Expose
    private Notification notification;

    public Message(String token, Notification notification) {
        this.token = token;
        this.notification = notification;
        this.to=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
