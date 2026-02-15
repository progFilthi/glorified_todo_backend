package com.product.glorifiedtodo.controllers;

import com.product.glorifiedtodo.dtos.CreateTodoRequestDto;
import com.product.glorifiedtodo.dtos.CreateTodoResponseDto;
import com.product.glorifiedtodo.services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = {"http://localhost:3000", "https://glorified-todo.vercel.app/"})
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<CreateTodoResponseDto> createTodo(@Valid @RequestBody CreateTodoRequestDto dto){
        CreateTodoResponseDto response = todoService.createTodo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CreateTodoResponseDto>> getAllTodos(@PageableDefault(size = 5, sort = "name") Pageable pageable){

        Page<CreateTodoResponseDto> response = todoService.getAllTodos(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateTodoResponseDto> getTodo(@PathVariable Long id){
        CreateTodoResponseDto response = todoService.getTodo(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateTodoResponseDto> updateTodo(@Valid @RequestBody CreateTodoRequestDto dto,
                                                            @PathVariable Long id){
        CreateTodoResponseDto response = todoService.updateTodo(dto, id);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //Mark todo as done or completed
    @PatchMapping("/{id}/complete")
    public ResponseEntity<CreateTodoResponseDto> completeTodo(@PathVariable Long id){
        CreateTodoResponseDto response = todoService.completeTodo(id);
        return ResponseEntity.ok(response);
    }

    //Re-open todo
    @PatchMapping("/{id}/re-open")
    public ResponseEntity<CreateTodoResponseDto> reOpenTodo(@PathVariable Long id){
        CreateTodoResponseDto response = todoService.reOpenTodo(id);
        return ResponseEntity.ok(response);
    }
}
