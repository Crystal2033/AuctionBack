package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.ProductExistsException;
import com.debugteam.auction_test.exceptions.ProductNotExistException;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductRequest productRequest, String user_id) throws ProductNotExistException;
    void deleteLot(String productId) throws ProductNotExistException;
    ProductDto getProduct(String productId) throws  ProductNotExistException;
    List<ProductDto> getProducts(String productName);
}
