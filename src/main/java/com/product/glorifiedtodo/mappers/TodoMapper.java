package com.product.glorifiedtodo.mappers;

import com.product.glorifiedtodo.dtos.CreateTodoRequestDto;
import com.product.glorifiedtodo.dtos.CreateTodoResponseDto;
import com.product.glorifiedtodo.models.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Todo toEntity(CreateTodoRequestDto dto);


    CreateTodoResponseDto toResponseDto(Todo todo);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromDto(CreateTodoRequestDto dto, @MappingTarget Todo todo);

}
