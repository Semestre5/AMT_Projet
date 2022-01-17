package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginCommand {
    public AuthLoginCommand(){

    }
    public AuthLoginCommand(String username, String role){
        this.username = username;
        this.password = role;
    }
    private String username;
    private String password;
}
