package com.example.shopping.users;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() { super("Quantity needs to be > 0.");}
}
