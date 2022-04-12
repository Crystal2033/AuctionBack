package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.ProductExistsException;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;

public interface ProductService {
    ProductDto addProduct(ProductRequest productRequest, String user_id) throws ProductExistsException;
}
