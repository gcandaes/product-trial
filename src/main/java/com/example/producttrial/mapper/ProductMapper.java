package com.example.producttrial.mapper;

import com.example.producttrial.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product mapUpdateProduct(Product originalProduct, Product productUpdated) {
        if (productUpdated.getCategory() != null) {
            originalProduct.setCategory(productUpdated.getCategory());
        }

        if (productUpdated.getCode() != null) {
            originalProduct.setCode(productUpdated.getCode());
        }

        if (productUpdated.getName() != null) {
            originalProduct.setName(productUpdated.getName());
        }

        if (productUpdated.getPrice() != originalProduct.getPrice()) {
            originalProduct.setPrice(productUpdated.getPrice());
        }
        //not present in the front form
        //originalProduct.setRating(productUpdated.getRating());
        if(productUpdated.getDescription() != null){
            originalProduct.setDescription(productUpdated.getDescription());
        }
        if(originalProduct.getQuantity() != 0 && productUpdated.getQuantity() != originalProduct.getQuantity()) {
            originalProduct.setQuantity(productUpdated.getQuantity());
        }
        if(productUpdated.getInventoryStatus() != null) {
            originalProduct.setInventoryStatus(productUpdated.getInventoryStatus());
        }
        return originalProduct;
    }
}
