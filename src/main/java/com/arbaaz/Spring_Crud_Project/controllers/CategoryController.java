package com.arbaaz.Spring_Crud_Project.controllers;

import com.arbaaz.Spring_Crud_Project.models.Category;
import com.arbaaz.Spring_Crud_Project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    CategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> getCategories( @RequestParam int page){
        return categoryService.getCategories(page);
    }


    @PostMapping
    public ResponseEntity<?> createCategory( @RequestBody @Valid Category category){
        return categoryService.createCategory(category);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById( @PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(  @PathVariable int id){
        return categoryService.deleteCategoryById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById( @PathVariable int id, @RequestBody Category category){
        return categoryService.updateCategoryById(id,category);
    }


}
