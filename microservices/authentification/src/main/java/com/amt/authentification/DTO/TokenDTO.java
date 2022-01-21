package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class TokenDTO {
    public TokenDTO(String token, AccountInfoDTO accountInfoDTO){
        this.token = token;
        this.account = accountInfoDTO;
    }
    @XmlElement(name = "token")
    String token;
    @XmlElement(name = "account")
    AccountInfoDTO account;
}
