package io.ducnt.todo.services;

import io.ducnt.todo.domains.Todo;
import io.ducnt.todo.events.TodoCreationEvent;
import io.ducnt.todo.repositories.TodoRepository;
import io.ducnt.todo.repositories.TodoTypeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoTypeRepository todoTypeRepository;
    private final Validator validator;

    @Autowired
    public TodoService(TodoRepository todoRepository, TodoTypeRepository todoTypeRepository, Validator validator) {
        this.todoRepository = todoRepository;
        this.todoTypeRepository = todoTypeRepository;
        this.validator = validator;
    }

    /**
     * listener method to handle the TodoCreationEvent
     using the After Commit transaction phase
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTodoCreationEvent(TodoCreationEvent event) {
        System.out.println("Handled TodoCreationEvent...");
    }

    @Transactional
    public Todo create(Todo todo) {
        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        for (ConstraintViolation<Todo> violation : violations) {
            System.out.println(violation.getMessage());
        }

        if (violations.isEmpty()) {
            todo.publishEventAfterSave();
            todo = todoRepository.save(todo);
        } else {
            return null;
        }

        return todo;
    }

    public Todo findById(Long id) {
        Optional<Todo> todoResult = todoRepository.findById(id);
        return todoResult.orElse(null);
    }

    public Todo update(Todo todo) {
        todo.setLastUpdated(new Date());
        if (todo.isDone()) {
            todo.setDateDone(new Date());
        }
        todo = todoRepository.save(todo);
        return todo;
    }

    public void deleteById(Long id) throws Exception {
        if (!todoRepository.existsById(id)) {
            throw new Exception("Id doesn't exist");
        }
        todoRepository.deleteById(id);
    }

    public List<Todo> findAll(String sort, Sort.Direction order, int pageNumber, int pageSize) {
        Sort sortObject = Sort.by(order, sort);
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sortObject);
        Page<Todo> todoPages = todoRepository.findAll(pageRequest);
        return todoPages.getContent();
    }
}
