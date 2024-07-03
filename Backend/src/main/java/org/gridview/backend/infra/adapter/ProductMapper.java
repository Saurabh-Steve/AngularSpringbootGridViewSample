package org.gridview.backend.infra.adapter;


import org.gridview.backend.domain.Page;
import org.gridview.backend.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public Page<Product> map(org.springframework.data.domain.Page<org.gridview.backend.infra.entity.Product> pdts) {
        List products = pdts.getContent().stream().map(p -> new Product(p.getId(), p.getName(), p.getPrice()
        ,p.getRating(), p.getImage(), p.getDescription())).toList();
        return new Page<Product>(products, pdts.getNumber(), pdts.hasNext(), pdts.getTotalPages());
    }

    public Product reverseMap(org.gridview.backend.infra.entity.Product entity) {
        return Product.builder().id(entity.getId()).name(entity.getName()).price(entity.getPrice())
                .rating(entity.getRating()).image(entity.getImage()).description(entity.getDescription()).build();
    }

    public org.gridview.backend.infra.entity.Product map(Product product) {
        return org.gridview.backend.infra.entity.Product.builder().id(product.getId()).name(product.getName())
                .price(product.getPrice()).rating(product.getRating()).image(product.getImage()).description(product.getDescription()).build();

    }
}
