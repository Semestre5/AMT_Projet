package com.amt.authentification;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    void setUp(){
        String name = "AxelVallon1";
        String password = "axelA84f_31";
        String role = "Admin";
        user = new User(name,password,role);
    }

    @AfterEach
    void delete(){
        User.delete(user);
    }

    @Test
    void register() {
        User.register(user);
        User user2 = User.fetchOneByName("AxelVallon1");
        // we set the same id to compare
        user.setId(user2.getId());
        assertEquals(user, user2);
    }
}