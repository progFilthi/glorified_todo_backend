package com.product.glorifiedtodo.services;

import com.product.glorifiedtodo.dtos.CreateTodoRequestDto;
import com.product.glorifiedtodo.dtos.CreateTodoResponseDto;
import com.product.glorifiedtodo.enums.Status;
import com.product.glorifiedtodo.exceptions.ResourceConflict;
import com.product.glorifiedtodo.exceptions.ResourceNotFound;
import com.product.glorifiedtodo.mappers.TodoMapper;
import com.product.glorifiedtodo.models.Todo;
import com.product.glorifiedtodo.repos.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
    private final TodoMapper todoMapper;


    @Transactional
    public CreateTodoResponseDto createTodo(CreateTodoRequestDto dto){


        if(todoRepo.existsByName(dto.name())){
            throw new ResourceConflict("Todo already exists.");
        }

        Todo todo = todoMapper.toEntity(dto);


        Todo savedTodo = todoRepo.save(todo);


        return todoMapper.toResponseDto(savedTodo);
    }

    @Transactional(readOnly = true)
    public Page<CreateTodoResponseDto> getAllTodos(Pageable pageable) {
        return todoRepo.findAll(pageable)
                .map(todoMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public CreateTodoResponseDto getTodo(Long id){
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo Not Found.")
        );

        return todoMapper.toResponseDto(todo);
    }

    @Transactional
    public CreateTodoResponseDto updateTodo(CreateTodoRequestDto dto, Long id){
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo Not Found.")
        );

        todoMapper.updateFromDto(dto, todo);

        return todoMapper.toResponseDto(todo);
    }

    @Transactional
    public void deleteTodo(Long id){
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo Not Found.")
        );

        todoRepo.delete(todo);
    }

    //Mark todo as done or completed
    @Transactional
    public CreateTodoResponseDto completeTodo(Long id){
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo Not Found.")
        );

        //Keeps it idempotent
        if(todo.getStatus() == Status.COMPLETED){
            todoMapper.toResponseDto(todo);
        }

        todo.setStatus(Status.COMPLETED);

        return todoMapper.toResponseDto(todo);
    }

    //Re-open todo
    @Transactional
    public CreateTodoResponseDto reOpenTodo(Long id){
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Todo Not Found.")
        );

        todo.setStatus(Status.PENDING);

        return todoMapper.toResponseDto(todo);
    }
}
