package com.example.api02.repository.search;

import com.example.api02.domain.Product;

import com.example.api02.domain.QProduct;
import com.example.api02.dto.ProductDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;


@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {

        super(Product.class);

    }


    @Override
    public Page<Product> getList(Pageable pageable) {

        log.info("getList......................");

        QProduct product = QProduct.product;


        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.tags);

        query.groupBy(product);

        getQuerydsl().applyPagination(pageable, query);




        log.info(query);



        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    @Override
    public Page<Object[]> getList2(Pageable pageable) {

        log.info("getList2......................");

        QProduct product = QProduct.product;


        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.tags);
        query.leftJoin(product.images);

        query.where(product.images.any().ord.eq(0));
        query.groupBy(product);

        getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<Tuple> tupleJPQLQuery = query.select(product.pid, product.pname, product.price, product.images.any());

        log.info(query);

        List<Object[]> list = tupleJPQLQuery.fetch().stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());


        return new PageImpl<>( list, pageable, tupleJPQLQuery.fetchCount()  );
    }
}
