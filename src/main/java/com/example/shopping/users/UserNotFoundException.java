package com.example.shopping.users;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("ID:" + id);
    }
}
