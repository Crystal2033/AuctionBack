package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.ProductNotExistException;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.models.ProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private HashMap<Integer, ProductRequest> savedProducts = new HashMap<>();

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Integer productId) throws ProductNotExistException {
        if (!savedProducts.containsKey(productId)) {
            throw new ProductNotExistException();
        }
        ProductResponse foundProduct = convertToResponse(savedProducts.get(productId));
        return foundProduct;
    }

    @GetMapping("")
    public ArrayList<ProductResponse> getProducts() {
        ArrayList<ProductResponse> result = new ArrayList<>();
        for (Map.Entry<Integer, ProductRequest> entry : savedProducts.entrySet()) {
            ProductResponse productResp = convertToResponse(entry.getValue());
            result.add(productResp);
        }
        return result;
    }

    @PostMapping("")
    public Integer addProduct(ProductRequest newProduct) {
        Integer id = savedProducts.size();
        savedProducts.put(id, newProduct);
        return id;
    }

    @DeleteMapping("/{id}")
    public Integer deleteProduct(@PathVariable("id") Integer productId) throws ProductNotExistException //but its strange. How did you get id of not existing product.
    {
        if (!savedProducts.containsKey(productId)) {
            throw new ProductNotExistException();
        }
        savedProducts.remove(productId);
        return productId;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      private
    ///////////////////////////////////////////////////////////////////////////
    private ProductResponse convertToResponse(ProductRequest productReq) {
        ProductResponse productResp = new ProductResponse();
        productResp.setProductName(productReq.getProductName());
        return productResp;
    }
}
