package com.example.api02.dto;

import com.example.api02.domain.ProductStatus;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {


    private Long pid;

    private String pname;

    private int price;

    private ProductStatus status;

    private List<String> tags;



}
