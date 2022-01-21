package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
public class Error {
    public Error(){

    }
    public Error(String error){
        this.error = error;
    }
    @XmlElement(name="error")
    String error;
}
