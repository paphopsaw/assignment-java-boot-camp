package com.example.shopping.users;

import com.example.shopping.products.ProductNotFoundException;
import com.example.shopping.products.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserResponse userNotFound(UserNotFoundException e) {
        return new UserResponse("User " + e.getMessage() + " not found.");
    }
}
