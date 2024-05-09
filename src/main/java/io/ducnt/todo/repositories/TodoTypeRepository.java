package io.ducnt.todo.repositories;

import io.ducnt.todo.domains.TodoType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTypeRepository extends PagingAndSortingRepository<TodoType, String>, CrudRepository<TodoType, String> {
}
