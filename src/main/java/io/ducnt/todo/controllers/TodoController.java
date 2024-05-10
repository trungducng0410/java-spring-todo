package io.ducnt.todo.controllers;

import io.ducnt.todo.domains.Todo;
import io.ducnt.todo.services.TodoService;
import io.ducnt.todo.services.TodoTypeService;
import io.ducnt.todo.utils.aop.LogMethodDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private TodoService todoService;
    private TodoTypeService todoTypeService;

    @Autowired
    public TodoController(TodoService todoService, TodoTypeService todoTypeService) {
        this.todoService = todoService;
        this.todoTypeService = todoTypeService;
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo)  {
        return todoService.create(todo);
    }

    @GetMapping("/{id}")
    @LogMethodDetails
    public Todo read(@PathVariable long id) {
        return todoService.findById(id);
    }

    @PutMapping
    public Todo update(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            todoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @LogMethodDetails
    public List<Todo> findAll(@RequestParam String sort,
                              @RequestParam String order,
                              @RequestParam int pageNumber,
                              @RequestParam int pageSize) {
        return todoService.findAll(sort, Sort.Direction.fromString(order), pageNumber, pageSize);
    }
}
