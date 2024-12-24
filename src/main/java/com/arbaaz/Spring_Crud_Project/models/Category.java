package com.arbaaz.Spring_Crud_Project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 3,max = 20,message = "Category name must be between 3 to 20 ")
    @NotNull(message = "name is mandatory")
    private String name;


    @Size(min = 5,max = 150,message = "description must be between 5 to 150")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> productList;
}
