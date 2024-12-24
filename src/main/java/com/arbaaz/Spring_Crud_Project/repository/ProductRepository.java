package com.arbaaz.Spring_Crud_Project.repository;

import com.arbaaz.Spring_Crud_Project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
