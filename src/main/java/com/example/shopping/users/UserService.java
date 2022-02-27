package com.example.shopping.users;

import com.example.shopping.products.Product;
import com.example.shopping.products.ProductNotFoundException;
import com.example.shopping.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void setProductRepository(ProductRepository productRepository) {this.productRepository = productRepository;
    }

    public User getById(int id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException(id);
    }

    public void addToCart(int cartItemId, int userId, CartItemRequest cartItemRequest) {
        int productId = cartItemRequest.getProductId();
        int quantity = cartItemRequest.getQuantity();
        if (!quantityIsValid(quantity))
            throw new InvalidQuantityException();
        Optional<User> userResult = userRepository.findById(userId);
        if (userResult.isEmpty())
            throw new UserNotFoundException(userId);
        Optional<Product> productResult = productRepository.findById(productId);
        if (productResult.isEmpty())
            throw new ProductNotFoundException(userId);

        User user = userResult.get();
        Product product = productResult.get();
        //Get current cart
        List<CartItem> cart = userResult.get().getCart();
        //Create new item to be added
        CartItem newItem = new CartItem(cartItemId, user, product, quantity);
        //Add to cart
        cart.add(newItem);
        user.setCart(cart);
        userRepository.save(user);
    }

    private boolean quantityIsValid(int quantity) {
        return quantity > 0;
    }
}
