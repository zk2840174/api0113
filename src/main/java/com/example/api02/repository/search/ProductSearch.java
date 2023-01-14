package com.example.api02.repository.search;

import com.example.api02.domain.Product;
import com.example.api02.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {


    Page<Product> getList(Pageable pageable);

    Page<Object[]> getList2(Pageable pageable);

}
