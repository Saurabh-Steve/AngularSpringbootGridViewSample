package org.gridview.backend.application.controller;

import org.gridview.backend.application.controller.dto.ProductDto;
import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.gridview.backend.domain.Page;
import org.gridview.backend.domain.in.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
        ProductDto result = service.addProduct(product);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/products")
    public ResponseEntity<org.gridview.backend.domain.Page<ProductDto>> getAllProductsV2(@RequestParam(required = false) String name,
            @RequestParam int pageNo,@RequestParam int size, @RequestParam("sortType") String sortType,
            @RequestParam("order") String order) {
        Page<ProductDto> ProductPage = service.getAllProducts(name,pageNo, size, SortType.valueOf(sortType.toUpperCase()), SortingOrder.valueOf(order.toUpperCase()));
        return ResponseEntity.ok(ProductPage);
    }

    @PostMapping("/product/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product) {
        ProductDto result = service.updateProduct(product);
        return ResponseEntity.ok(result);
    }
}
