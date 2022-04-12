package com.debugteam.auction_test.services.impl;

import com.debugteam.auction_test.database.entities.AccountEntity;
import com.debugteam.auction_test.database.entities.LotEntity;
import com.debugteam.auction_test.database.entities.ProductEntity;
import com.debugteam.auction_test.database.repositories.AccountRepository;
import com.debugteam.auction_test.database.repositories.ProductRepository;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.ProductExistsException;
import com.debugteam.auction_test.exceptions.ProductNotExistException;
import com.debugteam.auction_test.models.ProductDto;
import com.debugteam.auction_test.models.ProductRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ProductDto getProduct(String productId) throws ProductNotExistException
    {
        Optional<ProductEntity> existedProduct = productRepository.findOptionalById(productId);
        ProductEntity product = existedProduct.orElseThrow(ProductNotExistException::new);
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProducts(String productName)
    {
        List<ProductEntity> productsEntity = productRepository.findAllByName(productName);
        List<ProductDto> productsDto = new ArrayList<>();

        for (ProductEntity productEntity : productsEntity) {
            productsDto.add(mapper.map(productEntity, ProductDto.class));
        }
        return productsDto;
    }

    @Override
    public ProductDto addProduct(ProductRequest productRequest, String user_id) throws ProductNotExistException
    {
        if (productRequest.getName() == null) {
            throw new ProductNotExistException();
        }

        AccountEntity accountEntity = accountRepository.getById(user_id);

        ProductEntity newProduct = mapper.map(productRequest, ProductEntity.class);
        newProduct.setUser(accountEntity);
        productRepository.save(newProduct);

        return mapper.map(newProduct, ProductDto.class);
    }

    @Override
    public void deleteLot(String productId) throws ProductNotExistException
    {
        Optional<ProductEntity> existedProduct = productRepository.findOptionalById(productId);
        ProductEntity product = existedProduct.orElseThrow(ProductNotExistException::new);
        productRepository.delete(product);
    }
}
