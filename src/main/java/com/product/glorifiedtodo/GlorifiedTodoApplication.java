package com.product.glorifiedtodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class GlorifiedTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlorifiedTodoApplication.class, args);
    }

}
