package com.amt.contact;

import java.io.Serializable;

public class ContactServletModel implements Serializable {
    private String name;
    private String mail;
    private String message;

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
