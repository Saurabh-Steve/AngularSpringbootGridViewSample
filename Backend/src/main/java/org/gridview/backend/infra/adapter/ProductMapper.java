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
}
