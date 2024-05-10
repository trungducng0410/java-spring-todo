package io.ducnt.todo.controllers;

import io.ducnt.todo.domains.Todo;
import io.ducnt.todo.services.TodoService;
import io.ducnt.todo.services.TodoTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TodoController.class)
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoTypeService todoTypeService;

    @Test
    public void givenTodo_whenGetTodo_thenReturnJson() throws Exception {
        Todo doLaundry = new Todo();
        doLaundry.setId(1L);
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());

        given(todoService.findById(1L)).willReturn(doLaundry);

        mvc.perform(get("/api/todo/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(doLaundry.getTitle())));
    }
}