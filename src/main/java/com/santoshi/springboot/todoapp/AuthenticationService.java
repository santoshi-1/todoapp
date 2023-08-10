package com.santoshi.springboot.todoapp;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String userName, String password) {
        boolean isValidUser = userName.equalsIgnoreCase("in28Minutes");
        boolean isValidPassword = password.equals("Dummy");
        return isValidUser && isValidPassword;
    }
}
