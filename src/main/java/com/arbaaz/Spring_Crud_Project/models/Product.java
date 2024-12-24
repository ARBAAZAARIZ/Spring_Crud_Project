package com.arbaaz.Spring_Crud_Project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,unique = true)
    @Size(min = 3,max = 30,message = "Product name should be between 3 to 30 character")
    @NotNull(message = "Product name is mandatory")
    private String name;

    @Size(min = 5,max = 150,message = "Description can between 5 to 150 character")
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;




}
