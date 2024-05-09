package io.ducnt.todo.controllers;

import io.ducnt.todo.domains.TodoType;
import io.ducnt.todo.services.TodoTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todoType")
public class TodoTypeController {

    private TodoTypeService todoTypeService;

    @Autowired
    public TodoTypeController(TodoTypeService todoTypeService) {
        this.todoTypeService = todoTypeService;
    }

    /**
     * expose GetMapping at the /hello endpoint
     * @return String
     */
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping
    public TodoType create(@RequestBody TodoType todoType) {
        return todoTypeService.create(todoType);
    }

    @GetMapping("/{code}")
    public TodoType read(@PathVariable("code") String code) {
        return todoTypeService.findByCode(code);
    }

    @PutMapping
    public TodoType update(@RequestBody @Valid TodoType todoType) {
        return todoTypeService.update(todoType);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity delete(@PathVariable("code") String code) {
        try {
            todoTypeService.deleteByCode(code);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<TodoType> findAll(@RequestParam String sort, @RequestParam String order, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return todoTypeService.findAll(sort, Sort.Direction.fromString(order), pageNumber, pageSize);
    }

}
