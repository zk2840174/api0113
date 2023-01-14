package com.example.api02.repository.search;


import com.example.api02.domain.QTodo;
import com.example.api02.domain.Todo;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {

        super(Todo.class);

    }

    @Override
    public Page<Todo> getList(Pageable pageable) {



        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        getQuerydsl().applyPagination(pageable, query);

        List<Todo> list = query.fetch();

        long count = query.fetchCount();


        return new PageImpl<>(list, pageable, count);
    }
}
