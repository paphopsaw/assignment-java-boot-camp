package com.example.shopping.users;

import com.example.shopping.products.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(int id, Product product, Order order, int quantity, double price) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
