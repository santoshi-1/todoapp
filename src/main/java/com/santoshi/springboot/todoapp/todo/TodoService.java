package com.santoshi.springboot.todoapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();

    static {
        todoList.add(new Todo(1, "Santoshi", "Learn AWS", LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(2, "Santoshi", "Learn Devops", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(3, "Santoshi", "Learn Full Stack Development", LocalDate.now().plusYears(2), false));
    }


    public List<Todo> findByUsername(String username) {
        return todoList;
    }
}
