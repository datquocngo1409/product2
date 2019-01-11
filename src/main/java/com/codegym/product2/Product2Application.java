package com.codegym.product2;

import com.codegym.product2.service.CategoryService;
import com.codegym.product2.service.ProductService;
import com.codegym.product2.service.impl.CategoryServiceImpl;
import com.codegym.product2.service.impl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Product2Application {

	public static void main(String[] args) {
		SpringApplication.run(Product2Application.class, args);
	}

	@Bean
	public ProductService productService(){
		return new ProductServiceImpl();
	}

	@Bean
	public CategoryService categoryService(){
		return new CategoryServiceImpl();
	}
}

