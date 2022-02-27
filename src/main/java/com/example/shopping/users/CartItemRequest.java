package com.example.shopping.users;

public class CartItemRequest {
    private int productId;
    private int quantity;

    public CartItemRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{ productId: " + productId + ", quantity: " + quantity + "}";
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
