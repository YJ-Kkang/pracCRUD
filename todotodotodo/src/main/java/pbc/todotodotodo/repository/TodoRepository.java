package pbc.todotodotodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbc.todotodotodo.entity.Todo;

// interface 끼리는 extends 를 해야한다.
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
