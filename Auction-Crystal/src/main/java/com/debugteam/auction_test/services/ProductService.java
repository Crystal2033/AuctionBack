package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.ProductNotExistException;
import com.debugteam.auction_test.exceptions.UserAccessViolationException;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.models.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductRequest productRequest, String user_id) throws ProductNotExistException;

    void deleteLot(String productId, String userId) throws ProductNotExistException, UserAccessViolationException;

    ProductDto getProduct(String productId) throws ProductNotExistException;

    List<ProductDto> getProducts(String productName);
}
