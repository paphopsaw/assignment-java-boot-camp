package com.example.shopping.users;

import com.example.shopping.products.Product;
import com.example.shopping.products.ProductNotFoundException;
import com.example.shopping.products.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void addToCart(int userId, CartItemRequest cartItemRequest) {
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
        int newItemId = cart.size() + 1;
        CartItem newItem = new CartItem(newItemId, user, product, quantity);
        //Add to cart
        cart.add(newItem);
        user.setCart(cart);
        userRepository.save(user);
    }

    public void checkout(int userId, PaymentRequest paymentRequest) {
        //Validate payment
        //(suppose valid for now)
        Optional<User> userResult = userRepository.findById(userId);
        if (userResult.isEmpty())
            throw new UserNotFoundException(userId);

        User user = userResult.get();
        if (user.getCart().isEmpty())
            throw new InvalidCheckoutException("Cart is empty");

        int newOrderId = user.getOrders().size() + 1;
        UserOrder order = new UserOrder(newOrderId);
        //Set Order contact
        order.setContact(user.getContact());
        //Get CartItems from User, sum amount of money while adding to Order.orderItems
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;
        List<CartItem> cartItems = user.getCart();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(
                    orderItems.size() + 1,
                    cartItem.getProduct(),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()
            );
            orderItems.add(orderItem);
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);
        //Set payment info
        order.setPaymentInfo(
                true,
                paymentRequest.getPaymentMethod(),
                paymentRequest.getCreditCardNo(),
                paymentRequest.getCreditCardName(),
                paymentRequest.getCreditCardMonth(),
                paymentRequest.getCreditCardYear(),
                paymentRequest.getCvc()
        );
        List<UserOrder> orders = user.getOrders();
        order.setUser(user);
        orders.add(order);
        user.setOrders(orders);
        //Clear cart
        clearCart(userId);
    }

    public void clearCart(int userId) {
        Optional<User> userResult = userRepository.findById(userId);
        if (userResult.isEmpty())
            throw new UserNotFoundException(userId);
        User user = userResult.get();
        List<CartItem> emptyCart = new ArrayList<>();
        user.setCart(emptyCart);
        userRepository.save(user);
    }

    private boolean quantityIsValid(int quantity) {
        return quantity > 0;
    }
}
