package com.product.glorifiedtodo.controllers;

import com.product.glorifiedtodo.dtos.CreateTodoResponseDto;
import com.product.glorifiedtodo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos/status")
@RequiredArgsConstructor
public class StatusController {


    private final TodoService todoService;


//    @PostMapping
//    public ResponseEntity<CreateTodoResponseDto> ldaldadlka



}
