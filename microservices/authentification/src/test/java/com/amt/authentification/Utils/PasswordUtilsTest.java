package com.amt.authentification.Utils;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    @Test
    void isValidPassword() {
        String invalidEmptyPassword = "";
        String invalidPassword= "invalid";
        String validPassword = "AbcrD582#c_ha";
        assertFalse(PasswordUtils.isValidPassword(invalidEmptyPassword));
        assertFalse(PasswordUtils.isValidPassword(invalidPassword));
        assertTrue(PasswordUtils.isValidPassword(validPassword));
    }

    @Test
    void createHashAndValidate() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String defaultPassword = "AbcrD582#c_ha";
        String differentPassword = "AbcrD582#c_had";
        String hash = PasswordUtils.createHash(defaultPassword);
        assertTrue(PasswordUtils.validatePassword(defaultPassword, hash));
        assertFalse(PasswordUtils.validatePassword(differentPassword, hash));
    }
}