package com.example.api02.service;


import com.example.api02.domain.Todo;
import com.example.api02.dto.*;
import com.example.api02.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;
    @Override
    public Long register(TodoRegisterDTO registerDTO) {

        log.info("----------------");
        log.info(registerDTO);

        Todo entity = modelMapper.map(registerDTO, Todo.class);

        Todo result = todoRepository.save(entity);

        return result.getTno();
    }

    @Override
    public PageResponseDTO<TodoListDTO> list(PageRequestDTO pageRequestDTO) {

        Page<Todo>  result = todoRepository.getList(pageRequestDTO.getPageable(Sort.by("tno").descending()));


        return new PageResponseDTO<>(
                pageRequestDTO,
                result.getContent().stream().map(todo -> modelMapper.map(todo, TodoListDTO.class)).collect(Collectors.toList()),
                result.getTotalElements());

    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public void remove(Long tno) {


        todoRepository.deleteById(tno);

    }
}


