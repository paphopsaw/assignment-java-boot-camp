package com.example.shopping.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void getOneProduct() {
        //Arrange
        Product product = new Product(
                1,
                "iPhone 13 128GB Red",
                29900.0,
                "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-13-product-red-select-2021?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1629907846000",
                4.9);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        //Act
        ProductResponse result1 = testRestTemplate.getForObject("/api/product/1", ProductResponse.class);
        ProductResponse result2 = testRestTemplate.getForObject("/api/product/5", ProductResponse.class);
        //Assert
        assertEquals(result1.getProductList().get(0).getName(), "iPhone 13 128GB Red");
        assertEquals(result2.getMessage(), "Product ID:5 not found.");
    }

    @Test
    void getManyProducts() {
        //Arrange
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
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        List<Product> productListAll = new ArrayList<>();
        productListAll.add(product1);
        productListAll.add(product2);
        productListAll.add(product3);
        productListAll.add(product4);
        when(productRepository.searchFromName("iPhone")).thenReturn(productList);
        when(productRepository.findAll()).thenReturn(productListAll);
        //Act
        ProductResponse result1 = testRestTemplate.getForObject("/api/products", ProductResponse.class);
        ProductResponse result2 = testRestTemplate.getForObject("/api/products?search=iPhone", ProductResponse.class);
        //Assert
        assertEquals(result1.getProductList().size(), 4);
        assertEquals(result2.getProductList().size(), 3);

    }
}