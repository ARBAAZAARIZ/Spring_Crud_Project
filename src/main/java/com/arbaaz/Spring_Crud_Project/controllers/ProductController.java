package com.arbaaz.Spring_Crud_Project.controllers;

import com.arbaaz.Spring_Crud_Project.models.Product;

import com.arbaaz.Spring_Crud_Project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<?> getAllProductByPageNo( @RequestParam int page){
        return productService.getAllProductByPageNo(page);
    }

    @PostMapping
    public ResponseEntity<?> createProduct( @RequestBody @Valid Product product){
        return productService.createProduct(product);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById( @PathVariable int id){
        return productService.getProductById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable int id, @RequestBody @Valid Product product){
        return productService.updateProductById(id,product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById( @PathVariable int id){
        return productService.deleteProductById(id);
    }
}
