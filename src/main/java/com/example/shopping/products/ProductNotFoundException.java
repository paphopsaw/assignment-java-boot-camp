package com.example.shopping.products;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("ID:" + id);
    }
}
