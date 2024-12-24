package com.arbaaz.Spring_Crud_Project.service;

import com.arbaaz.Spring_Crud_Project.models.Category;
import com.arbaaz.Spring_Crud_Project.repository.CategoryRepository;
import com.arbaaz.Spring_Crud_Project.responseWrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ResponseWrapper responseWrapper;


    public ResponseEntity<?> createCategory(Category category){
        Category savedCategory=categoryRepository.save(category);

        responseWrapper.setMessage("Saved your category");
        responseWrapper.setData(savedCategory);
        return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
    }



    public ResponseEntity<?> getCategoryById(int id){
        Category category=categoryRepository.findById(id).orElseThrow(
                ()->{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Given Id doesn't exist");
                }
        );
        responseWrapper.setMessage("Saved your category");
        responseWrapper.setData(category);
        return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
    }


    public ResponseEntity<?> deleteCategoryById(int id){
        Category category=categoryRepository.findById(id).orElseThrow(
                ()->{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Given Id doesn't exist");
                }
        );

        categoryRepository.deleteById(id);
        responseWrapper.setMessage("Successfully deleted Category named " + category.getName());
        responseWrapper.setData(null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

    public ResponseEntity<?> updateCategoryById(int id,Category category){
        Category foundCategory=categoryRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Given Id doesn't exist")
        );

        category.setId(foundCategory.getId());
        Category updatedCategory=categoryRepository.save(category);

        responseWrapper.setMessage("Saved your category");
        responseWrapper.setData(updatedCategory);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

    private static final int DEFAULT_PAGE_SIZE=3;

    public ResponseEntity<?> getCategories(int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id").ascending());
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        if (categoryPage.isEmpty())
        { responseWrapper.setMessage("No categories found for the specified page");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        }
        responseWrapper.setMessage("Fetched categories");
        responseWrapper.setData(categoryPage.getContent());
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

}
