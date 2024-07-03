package org.gridview.backend.infra.adapter;

import org.gridview.backend.application.controller.dto.SortType;
import org.gridview.backend.application.controller.dto.SortingOrder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortCategoryMapper {
    public Sort map(SortType sort, SortingOrder orderBy) {
        return null == orderBy ? getSortCriteria(sort).ascending() : orderBy.equals(SortingOrder.ASC)
                ? getSortCriteria(sort).ascending() : getSortCriteria(sort).descending();
    }

    private static Sort getSortCriteria(SortType sort) {
        if(sort == null)
            return Sort.by("id");
        else if( sort.equals(SortType.NAME) ){
            return Sort.by("name");
        }else if(sort.equals(SortType.PRICE)) {
            return Sort.by("price");
        }else  {
            return Sort.by("rating");
        }
    }
}
