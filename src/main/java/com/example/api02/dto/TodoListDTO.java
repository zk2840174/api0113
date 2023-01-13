package com.example.api02.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoListDTO {

    private Long tno;
    private String title;
    private String writer;
    private LocalDate dueDate;

    private boolean complete;
}
