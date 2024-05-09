package io.ducnt.todo.repositories;

import io.ducnt.todo.domains.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo,  Long>, CrudRepository<Todo, Long> {
}
