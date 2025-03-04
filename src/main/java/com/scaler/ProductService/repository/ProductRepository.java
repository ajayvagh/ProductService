package com.scaler.ProductService.repository;

import com.scaler.ProductService.models.Product;
//import org.hibernate.query.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository <Product, UUID> {


    // select * from products where name = {name}
    Product findFirstByNameAndCategory(String name, String category);

    // find all produucts
    List < Product > findAll();


    // find all products by category
    List < Product > findByCategory(String category);

    Page<Product> findAll(Pageable pageable);
}


/*
save -> insert, update
if insert - no need to add id
if update - id will be already there
so save works accordingly for both insert and update
 */