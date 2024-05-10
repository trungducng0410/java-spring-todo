package io.ducnt.todo.services;

import io.ducnt.todo.domains.Todo;
import io.ducnt.todo.repositories.TodoRepository;
import io.ducnt.todo.repositories.TodoTypeRepository;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

class TodoServiceTest {

    private TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    private TodoTypeRepository todoTypeRepository = Mockito.mock(TodoTypeRepository.class);
    private Validator validator = Mockito.mock(Validator.class);

    private TodoService service = new TodoService(todoRepository, todoTypeRepository, validator);

    @Test
    void whenUpdate_thenReturnTodo() {
        Todo doLaundry = new Todo();
        doLaundry.setId(1L);
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());
        doLaundry.setDone(true);

        // given
        Mockito.when(todoRepository.save(doLaundry)).thenReturn(doLaundry);

        // when
        Todo result = service.update(doLaundry);

        // then
        Assertions.assertNotNull(result.getDateDone());
    }

}