package com.example.api02.repository;

import com.example.api02.domain.Product;
import com.example.api02.domain.ProductImages;
import com.example.api02.domain.ProductStatus;
import com.example.api02.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInserts() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Product product = Product.builder()
                    .pname("Product---"+i)
                    .price(3000)
                    .status(ProductStatus.SALE)
                    .tags(Set.of(i+"tag1" , i+ "tag2", i+ " tag3"))

                    .images(Set.of(
                            ProductImages.builder()
                                .uuid(UUID.randomUUID().toString())
                                .fname(i+"File").build() ,
                            ProductImages.builder()
                                    .uuid(UUID.randomUUID().toString())
                                    .fname(i+"File").build()
                            )
                    ).build();

            productRepository.save(product);

        });
    }

    @Test
    //@Transactional
    public void testList() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("pid").descending());

        Page<Product> result = productRepository.getList(pageable);


        result.get().forEach(product -> {

            log.info(product);

        });
    }

    @Test
    public void testDelete() {

        Long pid = 1L;

        productRepository.deleteById(pid);

    }


    @Test
    //@Transactional
    public void testList2() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("pid").descending());

        Page<Object[]> result = productRepository.getList2(pageable);


        result.get().forEach(arr -> {

            log.info(Arrays.toString(arr));

        });
    }
}
