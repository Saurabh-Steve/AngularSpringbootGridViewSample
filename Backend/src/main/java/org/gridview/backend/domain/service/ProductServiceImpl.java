package org.gridview.backend.domain.service;

import org.gridview.backend.application.controller.dto.ProductDto;
import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.gridview.backend.domain.Page;
import org.gridview.backend.domain.Product;
import org.gridview.backend.domain.in.ProductService;
import org.gridview.backend.infra.port.ProductPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductPersistencePort productPersistencePort;

    @Override
    public Page<ProductDto> getAllProducts(String name, int pageNo, int size, SortType sort, SortingOrder order) {
        String searchCriteria = name == null ? "*" : name;
        Page<Product> page= productPersistencePort.findAllProductsLikeName(searchCriteria, PageRequest.of(pageNo, size), sort, order);
        List products = page.getContent().stream().map(p -> new Product(p.getId(), p.getName(), p.getPrice(), p.getRating(), p.getImage(), p.getDescription())).toList();
        return new Page<ProductDto>(products, page.getNumber(), page.isHasNext(), page.getTotalPages());
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        Product updatedProduct = productPersistencePort.updateProduct(new Product(product.getId(), product.getName(), product.getPrice(), product.getRating(), product.getImage(), product.getDescription()));
        return new ProductDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getRating(), updatedProduct.getImage(), updatedProduct.getDescription());
    }
}
