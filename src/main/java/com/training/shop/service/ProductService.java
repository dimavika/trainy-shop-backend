package com.training.shop.service;

import com.training.shop.entity.Product;
import com.training.shop.repository.ProductRepo;
import com.training.shop.service.exception.NotFoundEntityException;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class ProductService implements Service<Product>  {

    private final ProductRepo productRepo;

    private static final String NOT_EXIST_ID = "Not exist product  with id = %s";

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public Product create(Product entity) {
        return productRepo.save(entity);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(()
                -> new NotFoundEntityException(String.format(NOT_EXIST_ID, id)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Product update(Product entity) {
        return productRepo.save(entity);
    }

    public boolean existsById(Long id){
        return productRepo.existsById(id);
    }
}
