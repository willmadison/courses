package com.toccorp.problemsolving.todoey.todos

import org.springframework.stereotype.Component
import java.util.*

@Component
class InMemoryTodoStore: TodoStore {
    private val store = mutableMapOf<String, Todo>()

    override fun save(todo: Todo): Todo {
        if (todo.id.isBlank()) {
            todo.id = UUID.randomUUID().toString()
        }

        todo.userId = todo.userId.trim().toLowerCase()
        store[todo.id] = todo

        return todo
    }

    override fun find(searchParameters: SearchParameters): Collection<Todo> {
        return store.values
                .filter { if (searchParameters.userId.isNotBlank()) it.userId.equals(searchParameters.userId, true) else true }
                .filter { if (searchParameters.todoId.isNotBlank()) it.id.equals(searchParameters.todoId, true) else true }
    }
}