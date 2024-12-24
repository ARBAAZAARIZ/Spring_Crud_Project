package com.arbaaz.Spring_Crud_Project.repository;

import com.arbaaz.Spring_Crud_Project.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
