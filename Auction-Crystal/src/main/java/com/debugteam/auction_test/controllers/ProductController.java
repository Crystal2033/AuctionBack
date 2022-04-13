package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.ProductNotExistException;
import com.debugteam.auction_test.exceptions.UserAccessViolationException;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") String productId) throws ProductNotExistException {
        return productService.getProduct(productId);
    }

    @GetMapping("/all/{name}")
    public List<ProductDto> getProducts(@PathVariable("name") String productName) {
        return productService.getProducts(productName);
    }

    @PostMapping("")
    public ProductDto addProduct(@RequestBody ProductRequest newProduct, OurAuthToken ourAuthToken) throws ProductNotExistException {
        return productService.addProduct(newProduct, ourAuthToken.getPrincipal().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") String productId, OurAuthToken authToken) throws ProductNotExistException,
            UserAccessViolationException
    {
        productService.deleteLot(productId, authToken.getPrincipal().getId());
    }

}