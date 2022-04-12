package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.entities.ProductEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.ProductRepository;
import com.debugteam.auction_test.exceptions.ProductExistsException;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, AccountRepository accountRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto addProduct(ProductRequest productRequest, String user_id) throws ProductExistsException
    {
        if (productRequest.getName() != null && productRepository.existsByName(productRequest.getName())) {
            throw new ProductExistsException();
        }

        AccountEntity accountEntity = accountRepository.getById(user_id);

        ProductEntity newProduct = mapper.map(productRequest, ProductEntity.class);
        newProduct.setUser(accountEntity);
        productRepository.save(newProduct);

        return mapper.map(newProduct, ProductDto.class);
    }
}
