package io.ducnt.todo.integration;

import io.ducnt.todo.domains.Todo;
import io.ducnt.todo.repositories.TodoRepository;
import io.ducnt.todo.repositories.TodoTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

@DataJpaTest
public class TodoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoTypeRepository todoTypeRepository;

    @Test
    public void whenTodoObjIsSaved_thenTodoObjIsPersisted() {
        Todo doLaundry = new Todo();
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());

        // when
        todoRepository.save(doLaundry);

        // then
        Assertions.assertEquals(doLaundry, todoRepository.findById(doLaundry.getId()).get());
    }

}
