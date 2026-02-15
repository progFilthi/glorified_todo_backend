package com.product.glorifiedtodo.repos;

import com.product.glorifiedtodo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Long> {
    boolean existsByName( String name);
}
