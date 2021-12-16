package com.amt.authentification;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class JWT {
    public String generateToken(User user){
        JwtBuilder token = Jwts.builder();
        token.claim("role", user.getRole());
        token.claim("id", user.getId());
        token.claim("username", user.getName());
        token.setSubject(user.getName());
        token.setExpiration(new Date(System.currentTimeMillis() + 3600 * 24 * 1000));
        return token.compact();
    }


}
