package org.gridview.backend.application.controller;

import org.gridview.backend.application.controller.dto.ProductDto;
import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.gridview.backend.domain.Page;
import org.gridview.backend.domain.in.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<org.gridview.backend.domain.Page<ProductDto>> getAllProducts(
            @RequestParam int pageNo,@RequestParam int size, @RequestParam("sortType") String sortType,
            @RequestParam("order") String order) {
        Page<ProductDto> ProductPage = service.getAllProducts(pageNo, size, SortType.valueOf(sortType.toUpperCase()), SortingOrder.valueOf(order.toUpperCase()));
        return ResponseEntity.ok(ProductPage);
    }

}
