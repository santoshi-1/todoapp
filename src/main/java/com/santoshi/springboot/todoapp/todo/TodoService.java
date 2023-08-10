package com.santoshi.springboot.todoapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static int todoCount = 0;
    private static List<Todo> todoList = new ArrayList<>();

    static {
        todoList.add(new Todo(++todoCount, "in28Minutes", "Learn AWS", LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(++todoCount, "in28Minutes", "Learn Devops", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(++todoCount, "in28Minutes", "Learn Full Stack Development", LocalDate.now().plusYears(2), false));
    }


    public List<Todo> findByUsername(String username) {
        return todoList;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        todoCount += 1;
        Todo todo = new Todo(todoCount, username, description, targetDate, isDone);
        todoList.add(todo);
    }
}
