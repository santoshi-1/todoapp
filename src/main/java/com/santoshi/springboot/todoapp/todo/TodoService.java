package com.santoshi.springboot.todoapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static int todoCount = 0;
    private static List<Todo> todoList = new ArrayList<>();

    static {
        todoList.add(new Todo(++todoCount, "Santoshi", "Learn AWS", LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(++todoCount, "Santoshi", "Learn Devops", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(++todoCount, "Santoshi", "Learn Full Stack Development", LocalDate.now().plusYears(2), false));
    }


    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todoList.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
        todoCount += 1;
        Todo todo = new Todo(todoCount, username, description, targetDate, isDone);
        todoList.add(todo);
    }

    public void deleteTodoById(int id) {
        //todo.getId() == id
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todoList.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todoList.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodoById(todo.getId());
        todoList.add(todo);
    }
}
