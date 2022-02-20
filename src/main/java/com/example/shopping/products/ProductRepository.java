package com.example.shopping.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("from Product as product where product.name like %?1%")
    List<Product> searchFromName(String searchWord);
}
