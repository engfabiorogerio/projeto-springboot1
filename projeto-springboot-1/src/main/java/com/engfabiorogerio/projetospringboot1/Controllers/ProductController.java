package com.engfabiorogerio.projetospringboot1.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.engfabiorogerio.projetospringboot1.dtos.ProductRecordDTO;
import com.engfabiorogerio.projetospringboot1.models.ProductModel;
import com.engfabiorogerio.projetospringboot1.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {

		var productModel = new ProductModel();
		BeanUtils.copyProperties(productRecordDTO, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));

	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProductModel> product0 = productRepository.findById(id);
		if (product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(product0.get());
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateproduct(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ProductRecordDTO productRecordDTO) {

		Optional<ProductModel> product0 = productRepository.findById(id);
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found.");
		}
		
		var productModel = product0.get();
		BeanUtils.copyProperties(productRecordDTO, productModel);
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
				
	}
}
