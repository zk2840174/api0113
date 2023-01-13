package com.example.api02.repository;


import com.example.api02.domain.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Todo todo = Todo.builder()
                    .title("Title.." + i)
                    .writer("user00")
                    .dueDate(LocalDate.now().plusDays( i % 30))
                    .build();

            todoRepository.save(todo);
        });
    }

}
