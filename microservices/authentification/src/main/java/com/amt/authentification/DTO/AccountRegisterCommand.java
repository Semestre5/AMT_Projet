package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountRegisterCommand implements Serializable {
    String username;
    String role;
}
