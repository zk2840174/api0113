package com.example.api02.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoRegisterDTO {

    private String title;

    private String writer;

    private LocalDate dueDate;
}
