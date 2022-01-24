package com.amt.authentification.DTO;

import com.amt.authentification.User;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class AccountInfoDTO {
    @XmlElement(name ="id")
    Integer id;
    @XmlElement(name = "username")
    String username;
    @XmlElement(name = "role")
    String role;
    public AccountInfoDTO(User user){
        this.id = user.getId();
        this.username = user.getName();
        this.role = user.getRole();
    }
}
