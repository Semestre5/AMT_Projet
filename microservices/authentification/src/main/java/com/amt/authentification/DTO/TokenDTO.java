package com.amt.authentification.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    String token;
    AccountInfoDTO account;
}
