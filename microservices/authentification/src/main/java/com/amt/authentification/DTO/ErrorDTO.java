package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter @Setter
@XmlRootElement
public class ErrorDTO {
    @XmlElement(name = "property")
    String property;
    @XmlElement(name = "message")
    String message;

    public ErrorDTO() {

    }

    public ErrorDTO(String property, String msg) {
        this.property = property;
        this.message = msg;
    }
}
