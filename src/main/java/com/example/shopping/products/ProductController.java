package com.example.shopping.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/api/product/{id}")
    public ProductResponse getOneProduct(@PathVariable int id) {
        List<Product> productList = new ArrayList<>();
        productList.add(productService.getById(id));
        return new ProductResponse(productList);
    }

    @GetMapping("/api/products")
    public ProductResponse getManyProducts(@RequestParam(required = false) String search) {
        if (nonNull(search)) {
            return new ProductResponse(productService.search(search));
        }
        return new ProductResponse(productService.getAll());
    }
}
