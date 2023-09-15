package com.engfabiorogerio.projetospringboot1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engfabiorogerio.projetospringboot1.models.ProductModel;

@Repository
public interface ProductInterface extends JpaRepository<ProductModel, UUID> {

}
