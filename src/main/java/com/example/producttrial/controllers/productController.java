package com.example.producttrial.controllers;

import com.example.producttrial.entities.Product;
import com.example.producttrial.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@CrossOrigin(origins = "http://localhost:4200")
public class productController {

    @Autowired
    ProductService productService;

    @PostMapping(path="",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /*This method is not required and asked */
    @PostMapping(path = "/list",consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        productService.saveProducts(products);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = productService.getProducts();
        return productList.isEmpty() ? new ResponseEntity<>(productList, HttpStatus.NO_CONTENT) : new ResponseEntity<>(productList, HttpStatus.FOUND);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
       return  new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity patchProduct(@PathVariable Long id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        Product productUpdated =productService.patch(id, jsonPatch);

        return new ResponseEntity(productUpdated, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
