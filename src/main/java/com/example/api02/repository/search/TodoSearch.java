package com.example.api02.repository.search;

import com.example.api02.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {

    Page<Todo> getList(Pageable pageable);
}
