package io.ducnt.todo.services;

import io.ducnt.todo.domains.TodoType;
import io.ducnt.todo.repositories.TodoTypeRepository;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

class TodoTypeServiceTest {

    private TodoTypeRepository todoTypeRepository = Mockito.mock(TodoTypeRepository.class);
    private Validator validator = Mockito.mock(Validator.class);
    private TodoTypeService service = new TodoTypeService(todoTypeRepository, validator);

    @Test
    void whenReadTodoType_thenReturnTodoType() {
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDateCreated(new Date());
        Optional<TodoType> personalOptional = Optional.of(personal);

        // given
        Mockito.when(todoTypeRepository.findById("PERSONAL")).thenReturn(personalOptional);

        // when
        TodoType result = service.findByCode("PERSONAL");

        //then
        Assertions.assertEquals(personal.getCode(), "PERSONAL");
    }
}