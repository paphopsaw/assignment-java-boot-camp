package com.example.shopping.users;

import com.example.shopping.products.Product;
import com.example.shopping.products.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    private User mockUpUserData() {
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

    private List<Product> mockUpProductData() {
        Product product1 = new Product(
                1,
                "iPhone 13 128GB Red",
                29900.0,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-13-product-red-select-2021?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1629907846000",
                4.9);
        Product product2 = new Product(
                2,
                "iPhone 12 64GB Blue",
                25900.0,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-12-blue-select-2020?wid=940&hei=1112&fmt=png-alpha&.v=1604343704000",
                4.8);
        Product product3 = new Product(
                3,
                "iPhone 12 128GB Black",
                27900.0,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-12-black-select-2020?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1604343702000",
                4.8);
        Product product4 = new Product(
                4,
                "Apple watch series 7 Blue 45mm",
                14900.0,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MKUW3_VW_34FR+watch-45-alum-blue-nc-7s_VW_34FR_WF_CO?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=95&.v=1632171100000,1631661591000",
                5.0);
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        return list;
    }

    private User mockUpUserDataForPayment() {
        User user = new User(1, "Stock");
        Contact contact = new Contact(1,
                user,
                "10310",
                "Bangkok",
                "Huaikhwang",
                "238 Ratchadaphisek Rd Huaikhwang",
                "0929838940");
        user.setContact(contact);
        Product product1 = new Product(
                1,
                "iPhone 13 128GB Red",
                29900.0,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-13-product-red-select-2021?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1629907846000",
                4.9);
        CartItem cartItem = new CartItem(101, user, product1, 2);
        List<CartItem> cart = new ArrayList<>();
        cart.add(cartItem);
        user.setCart(cart);
        return user;
    }

    @Test
    void getById() {
        //Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUpUserData()));
        //Act
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        User user = userService.getById(1);
        //Assert
        assertEquals("Stock", user.getName());
        assertEquals("Bangkok", user.getContact().getProvince());
        assertEquals("Stock", user.getContact().getUser().getName());
    }

    @Test
    void addToCart() {
        //Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUpUserData()));
        when(productRepository.findById(2)).thenReturn(Optional.of(mockUpProductData().get(1)));
        CartItemRequest cartItemRequest = new CartItemRequest(2, 3);
        //Act
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        userService.setProductRepository(productRepository);
        userService.addToCart(1, cartItemRequest);
        User user = userService.getById(1);
        //Assert
        assertEquals(1, user.getCart().size());
        assertEquals(3, user.getCart().get(0).getQuantity());
        assertEquals("iPhone 12 64GB Blue", user.getCart().get(0).getProduct().getName());
        assertEquals(1, user.getCart().get(0).getId());
    }

    @Test
    void addToCart_invalid_quantity() {
        //Act
        UserService userService = new UserService();
        CartItemRequest cartItemRequest = new CartItemRequest(2, -2);
        //Assert
        assertThrows(InvalidQuantityException.class,
                () -> userService.addToCart(1, cartItemRequest));
    }

    @Test
    void checkout() {
        //Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUpUserDataForPayment()));

        PaymentRequest paymentRequest = new PaymentRequest(
                "Credit card",
                "1234123412341234",
                "Paphop Sawasdee",
                2,
                2026,
                "123"
        );
        //Act
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        userService.setProductRepository(productRepository);
        userService.checkout(1, paymentRequest);
        User user = userService.getById(1);
        //Assert
        assertEquals(0, user.getCart().size());
        assertEquals(1, user.getOrders().size());
        assertEquals(29900.0 * 2, user.getOrders().get(0).getTotalAmount());
        assertEquals("Bangkok", user.getOrders().get(0).getContact().getProvince());
    }
}