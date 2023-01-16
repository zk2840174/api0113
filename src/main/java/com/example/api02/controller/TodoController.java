package com.example.api02.controller;


import com.example.api02.annotations.JWTAuth;
import com.example.api02.dto.*;
import com.example.api02.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todos/")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public PageResponseDTO<TodoListDTO> list(PageRequestDTO pageRequestDTO){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return todoService.list(pageRequestDTO);
    }

    @PostMapping("")
    public Map<String, Long> register(@RequestBody TodoRegisterDTO registerDTO){


        log.info("-----------------------------------");
        log.info(registerDTO);
        log.info("-----------------------------------");

        Long tno = todoService.register(registerDTO);

        return Map.of("tno", tno);

    }

    @GetMapping("/{tno}")
    @JWTAuth(auth = true)
    public TodoDTO read(@PathVariable("tno") Long tno){


        TodoDTO todoDTO =  todoService.read(tno);

        log.info(todoDTO);

        return todoDTO;
    }

    @DeleteMapping("/{tno}")
    public void remove(@PathVariable("tno") Long tno){

        todoService.remove(tno);
    }

}

