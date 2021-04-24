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
public class Notification {
    @Expose
    private String title;
    @Expose
    private String body;
    @Expose
    private String to;

    public Notification(String title, String body, String to) {
        this.title = title;
        this.body = body;
        this.to=to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
