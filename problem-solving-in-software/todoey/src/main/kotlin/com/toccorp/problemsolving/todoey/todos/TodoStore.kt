package com.toccorp.problemsolving.todoey.todos

interface TodoStore {
    fun save(todo: Todo): Todo
    fun find(searchParameters: SearchParameters): Collection<Todo>
}
