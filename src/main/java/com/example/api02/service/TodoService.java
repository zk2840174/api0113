package com.example.api02.service;

import com.example.api02.dto.*;

public interface TodoService {



    Long register(TodoRegisterDTO registerDTO);

    PageResponseDTO<TodoListDTO> list(PageRequestDTO pageRequestDTO);


    TodoDTO read(Long tno);

    void remove(Long tno);

}
