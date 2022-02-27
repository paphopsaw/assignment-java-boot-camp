package com.example.shopping.users;

import com.example.shopping.products.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private User mockUpData() {
        User user = new User(1, "Stock");
        Contact contact = new Contact(1,
                user,
                "10310",
                "Bangkok",
                "Huaikhwang",
                "238 Ratchadaphisek Rd Huaikhwang",
                "0929838940");
        user.setContact(contact);
        return user;
    }

    @Test
    void getById() {
        //Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUpData()));
        //Act
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        User user = userService.getById(1);
        //Assert
        assertEquals("Stock", user.getName());
        assertEquals("Bangkok", user.getContact().getProvince());
        assertEquals("Stock", user.getContact().getUser().getName());

    }
}