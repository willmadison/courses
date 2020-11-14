package com.toccorp.problemsolving.todoey.configuration

import com.toccorp.problemsolving.todoey.todos.InMemoryTodoStore
import com.toccorp.problemsolving.todoey.todos.TodoStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TodoStoreConfig {

    @Bean
    fun todoStore(): TodoStore = InMemoryTodoStore()
}