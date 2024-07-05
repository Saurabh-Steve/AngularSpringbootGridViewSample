package org.gridview.backend.domain.in;

import org.gridview.backend.application.controller.dto.ProductDto;
import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.gridview.backend.domain.Page;

public interface ProductService {
    public Page<ProductDto> getAllProducts(String name, int pageNo, int size, SortType sort, SortingOrder orderBy);
    ProductDto updateProduct(ProductDto product);

    ProductDto addProduct(ProductDto product);
}
