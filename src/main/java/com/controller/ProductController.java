package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Repository.ProductRepository;
import com.entity.ProductEntity;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	// add product in the db (insert) 
	@PostMapping("/products")
	public ProductEntity addProduct(@RequestBody ProductEntity productEntity)
	{
		System.out.println(productEntity.getProductName());
		System.out.println(productEntity.getPrice());
		productRepository.save(productEntity);
		return productEntity;
	}
	 
	//read All products from database
	@GetMapping("/products")
	public List<ProductEntity> getAllProducts()
	{
		List <ProductEntity> products =productRepository.findAll();
		return products;
	}
	
	//get product details by Id through path variable
	@GetMapping("/products/{productId}")
	public ProductEntity getProductById(@PathVariable("productId") Integer productId)
	{
		Optional<ProductEntity> op = productRepository.findById(productId);
		if(op.isEmpty())
		{
			return null;
		}else
		{
			 ProductEntity productEntity =op.get();
			 return productEntity;
		}
		
	}
	
	//get product details by Id through request Param
	@GetMapping("/productsbyid")
	public ProductEntity getProductById2(@RequestParam("productId") Integer productId)
	{
		Optional<ProductEntity> op = productRepository.findById(productId);
		if(op.isEmpty())
		{
			return null;
		}else
		{
			 ProductEntity productEntity =op.get();
			 return productEntity;
		}
	}
	
	//api -> remove product from product table -> delete by id
	//input : productId
	@DeleteMapping("/products/{productId}")
	public String deleteProductById(@PathVariable("productId") Integer productId) {
		Optional<ProductEntity> op = productRepository.findById(productId);
		
		if(op.isEmpty())
		{
			return "Not Found";
		}
		else {
			productRepository.deleteById(productId);
			return "Success";
		}
	}
	
	//update
	//input : all product input that we can modify, productId must be present
	@PutMapping("/products")
	public String updateProduct(@RequestBody ProductEntity productEntity)
	{
		productRepository.save(productEntity);
		return "Success";
		
	}
}
