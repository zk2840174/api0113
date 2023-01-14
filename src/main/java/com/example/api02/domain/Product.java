package com.example.api02.domain;


import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Getter
@Table(name="tbl_product")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String pname;

    private int price;

    private ProductStatus status;

    @BatchSize(size = 100)
    @ElementCollection( fetch = FetchType.LAZY)
    @Builder.Default
    private Set<String> tags = new TreeSet<>();

    @BatchSize(size = 100)
    @ElementCollection( fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ProductImages> images = new TreeSet<>();


    public void clearImages() {

        images.clear();

    }

    public void changeImages(Set<ProductImages> imgs) {

        clearImages();

        imgs.forEach(productImages -> {
            productImages.setOrd(images.size());
            images.add(productImages);
        });
    }

}
