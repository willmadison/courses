package com.toccorp.problemsolving.todoey.todos

import org.springframework.stereotype.Component

@Component
class Todos(private val todoStore : InMemoryTodoStore) {

    fun forUser(userId: String): Collection<Todo> {
        val params = SearchParameters(userId = userId)
        return todoStore.find(params)
    }

    fun save(todo: Todo): Todo {
        return todoStore.save(todo)
    }
}