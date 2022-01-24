package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.NoJackson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@Getter
@Setter
@XmlRootElement
public class ErrorsDTO {
    @XmlElement(name="errors")
    ArrayList<ErrorDTO> errors = new ArrayList<ErrorDTO>();

    public ErrorsDTO(){
    }

    public void add(ErrorDTO error){
        errors.add(error);
    }

}
