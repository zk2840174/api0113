package com.example.api02.repository;

import com.example.api02.domain.Product;
import com.example.api02.repository.search.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductSearch {
}
