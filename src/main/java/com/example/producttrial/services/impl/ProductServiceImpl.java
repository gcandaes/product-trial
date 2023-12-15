package com.example.producttrial.services.impl;

import com.example.producttrial.entities.Product;
import com.example.producttrial.exceptions.ResourceNotFoundException;
import com.example.producttrial.mapper.ProductMapper;
import com.example.producttrial.repositories.ProductRepository;
import com.example.producttrial.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
    @Override
    public void saveProducts(List<Product> products) {
        for(Product product:products) {
            save(product);
        }
    }
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The product you want to access doesn't exist. Id = " + id));
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public Product patch(Long id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        Product product = getProduct(id);
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(product, JsonNode.class));

        return productRepository.save(objectMapper.treeToValue(patched, Product.class));
    }
}
