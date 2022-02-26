package com.example.shopping.products;

import java.util.List;

public class ProductResponse {

    public ProductResponse() {}

    public ProductResponse(String message) {
        this.message = message;
    }

    public ProductResponse(List<Product> productList) {
        this.productList = productList;
    }

    private List<Product> productList;
    private String message;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
