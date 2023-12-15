package com.example.producttrial.services;

import com.example.producttrial.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface ProductService {
    void save(Product product);

    void saveProducts(List<Product> products);

    List<Product> getProducts();

    Product getProduct(Long id);

    void deleteProduct(Long id);

    Product patch(Long id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException;


}