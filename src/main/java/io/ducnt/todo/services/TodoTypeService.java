package io.ducnt.todo.services;

import io.ducnt.todo.domains.TodoType;
import io.ducnt.todo.repositories.TodoTypeRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoTypeService {

    private final TodoTypeRepository todoTypeRepository;
    private final Validator validator;


    @Autowired
    public TodoTypeService(TodoTypeRepository todoTypeRepository, Validator validator) {
        this.todoTypeRepository = todoTypeRepository;
        this.validator = validator;
    }

    public TodoType create(TodoType todoType) {
        return todoTypeRepository.save(todoType);
    }

    public TodoType findByCode(String code) {
        return todoTypeRepository.findById(code).orElse(null);
    }

    public TodoType update(TodoType todoType) {
        todoType.setLastUpdated(new Date());
        todoType = todoTypeRepository.save(todoType);
        return todoType;
    }

    public void deleteByCode(String code) throws Exception {
        if (!todoTypeRepository.existsById(code)) {
            throw new Exception("Code doesn't exist");
        }
        todoTypeRepository.deleteById(code);
    }

    public List<TodoType> findAll(String sort, Sort.Direction order, int pageNumber, int pageSize) {
        Sort sortObject = Sort.by(order, sort);
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sortObject);
        Page<TodoType> todoTypePages = todoTypeRepository.findAll(pageRequest);
        return todoTypePages.getContent();
    }

}
