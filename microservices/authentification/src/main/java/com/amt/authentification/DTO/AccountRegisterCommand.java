package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@XmlRootElement
public class AccountRegisterCommand implements Serializable {
    @XmlElement(name= "username")
    String username;
    @XmlElement(name = "password")
    String password;
}
