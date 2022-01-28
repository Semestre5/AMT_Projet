package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class AuthLoginCommand {
    public AuthLoginCommand(){

    }
    public AuthLoginCommand(String username, String role){
        this.username = username;
        this.password = role;
    }
    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;
}
