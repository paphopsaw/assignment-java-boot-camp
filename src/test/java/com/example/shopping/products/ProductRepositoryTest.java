package com.example.shopping.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    void initializeData() {
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
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }

    @Test
    void findAll() {
        //Act
        List<Product> resultFindAll = productRepository.findAll();
        //Assert
        assertEquals(4, resultFindAll.size());
    }

    @Test
    void findById() {
        //Act
        Optional<Product> resultFindById = productRepository.findById(1);
        //Assert
        assertTrue(resultFindById.isPresent());
        assertEquals("iPhone 13 128GB Red", resultFindById.get().getName());
    }

    @Test
    void search() {
        List<Product> resultSearch = productRepository.searchFromName("iPhone");
        assertEquals(3, resultSearch.size());
    }
}