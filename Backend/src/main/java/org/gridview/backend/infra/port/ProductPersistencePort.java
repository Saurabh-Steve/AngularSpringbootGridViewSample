package org.gridview.backend.infra.port;

import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.gridview.backend.domain.Page;
import org.gridview.backend.domain.Product;
import org.springframework.data.domain.Pageable;


public interface ProductPersistencePort {
    public Page<Product> getAllProducts(Pageable pageable, SortType sort, SortingOrder orderBy);
}
