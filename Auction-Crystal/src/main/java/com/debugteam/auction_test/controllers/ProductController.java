package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.ProductExistsException;
import com.debugteam.auction_test.exceptions.ProductNotExistException;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Integer productId) throws ProductNotExistException {
        return new ProductDto();
    }

    @GetMapping("")
    public List<ProductDto> getProducts() {
        return new ArrayList<ProductDto>();
    }

    @PostMapping("")
    public ProductDto addProduct(@RequestBody ProductRequest newProduct, OurAuthToken ourAuthToken) throws ProductExistsException {
        return productService.addProduct(newProduct, ourAuthToken.getPrincipal().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Integer productId) throws ProductNotExistException //but its strange. How did you get id of not existing product.
    {

    }

    ///////////////////////////////////////////////////////////////////////////
    //                      private
    ///////////////////////////////////////////////////////////////////////////
    private ProductDto convertToResponse(ProductRequest productReq) {
        return new ProductDto();
    }
}
