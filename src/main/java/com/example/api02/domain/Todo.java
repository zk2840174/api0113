package com.example.api02.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name="tbl_todo2")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String title;

    private String writer;

    private LocalDate dueDate;

    private boolean complete;


}
