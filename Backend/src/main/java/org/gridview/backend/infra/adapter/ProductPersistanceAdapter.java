package org.gridview.backend.infra.adapter;

import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.gridview.backend.domain.Page;
import org.gridview.backend.domain.Product;
import org.gridview.backend.infra.port.ProductPersistencePort;
import org.gridview.backend.infra.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistanceAdapter implements ProductPersistencePort {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private SortCategoryMapper sortCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product addProduct(Product product) {
        org.gridview.backend.infra.entity.Product pdtEntity = productMapper.map(product);
        org.gridview.backend.infra.entity.Product saved = productRepository.save(pdtEntity);
        return productMapper.reverseMap(saved);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable, SortType sort, SortingOrder orderBy) {
        Pageable request = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()
                , sortCategoryMapper.map(sort, orderBy));
        return productMapper.map(productRepository.findAll(request));
    }

    @Override
    public Page<Product> findAllProductsLikeName(String name, Pageable pageable, SortType sort, SortingOrder orderBy) {
        Pageable request = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()
                , sortCategoryMapper.map(sort, orderBy));
        return productMapper.map(productRepository.findAllLikeName(name, request));
    }

    @Override
    public Product updateProduct(Product product) {
        org.gridview.backend.infra.entity.Product pdtEntity = productMapper.map(product);
        org.gridview.backend.infra.entity.Product savedPdt = productRepository.save(pdtEntity);
        return productMapper.reverseMap(savedPdt);
    }
}
