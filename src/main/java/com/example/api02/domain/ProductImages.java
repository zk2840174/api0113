package com.example.api02.domain;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductImages {

    private String uuid;
    private String fname;

    private int ord;
}
