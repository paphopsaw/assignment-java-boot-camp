package com.example.shopping.products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private List<Product> mockUpData() {
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

    @Test
    void getAll() {
        //Arrange
        when(productRepository.findAll()).thenReturn(mockUpData());
        //Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.getAll();
        //Assert
        assertEquals(4, result.size());
    }

    @Test
    void getById() {
        //Arrange
        when(productRepository.findById(1)).thenReturn(Optional.of(mockUpData().get(0)));
        //Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Product result = productService.getById(1);
        //Assert
        assertEquals("iPhone 13 128GB Red", result.getName());
    }

    @Test
    void search() {
        //Arrange
        when(productRepository.searchFromName("iPhone")).thenReturn(mockUpData().subList(0,3));
        //Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        List<Product> result = productService.search("iPhone");
        //Assert
        assertEquals(3, result.size());
    }
}