package com.arbaaz.Spring_Crud_Project.service;

import com.arbaaz.Spring_Crud_Project.models.Category;
import com.arbaaz.Spring_Crud_Project.models.Product;
import com.arbaaz.Spring_Crud_Project.repository.CategoryRepository;
import com.arbaaz.Spring_Crud_Project.repository.ProductRepository;
import com.arbaaz.Spring_Crud_Project.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ResponseWrapper responseWrapper;

    @Autowired
    CategoryRepository categoryRepository;




    private final static int CONST_PAGE_SIZE=6;


    public ResponseEntity<?> getAllProductByPageNo(int page) {
        Pageable pageable = PageRequest.of(page, CONST_PAGE_SIZE, Sort.by("id").ascending());
        Page<Product> products = productRepository.findAll(pageable);
        if (products.isEmpty()) {
            responseWrapper.setMessage("No Products found for the specified page");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        } else {
            responseWrapper.setMessage("Fetched Products");
            responseWrapper.setData(products.getContent());
            return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        }
    }
    public ResponseEntity<?> createProduct(Product product){
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Category found with given category id " +
                        product.getCategory().getId())
        );
        product.setCategory(category);
        Product savedProduct=productRepository.save(product);

        responseWrapper.setMessage("Successfully added product in database");
        responseWrapper.setData(savedProduct);

        return new ResponseEntity<>(responseWrapper,HttpStatus.CREATED);

    }






    public ResponseEntity<?> getProductById(int id){
        Product product=productRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found with given id")
        );

        responseWrapper.setMessage("Successfully added product in database");
        responseWrapper.setData(product);

        return new ResponseEntity<>(responseWrapper,HttpStatus.FOUND);
    }

    public ResponseEntity<?> updateProductById(int id,Product product){
        Product foundProduct =productRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "given id doesn't exist")
        );

        Category category=categoryRepository.findById(foundProduct.getCategory().getId()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Something went wrong with category id")
        );

        product.setId(id);
        product.setCategory(category);

        Product savedProduct=productRepository.save(product);

        responseWrapper.setMessage("Successfully updated product in database");
        responseWrapper.setData(savedProduct);

        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);

    }


    public ResponseEntity<?> deleteProductById(int id){
        Product foundProduct =productRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "given id doesn't exist")
        );

        productRepository.deleteById(id);

        responseWrapper.setMessage("Successfully deleted product from database");
        responseWrapper.setData(null);

        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);

    }




}
