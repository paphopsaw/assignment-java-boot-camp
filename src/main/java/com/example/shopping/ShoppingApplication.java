package com.example.shopping;

import com.example.shopping.products.Product;
import com.example.shopping.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ShoppingApplication {


	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void initializeData() {
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

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
