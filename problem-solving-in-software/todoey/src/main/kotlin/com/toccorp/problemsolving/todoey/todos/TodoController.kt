package com.toccorp.problemsolving.todoey.todos

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TodoController(val todos: Todos) {

    @GetMapping("/todos")
    fun todos(@RequestParam userId: String): Collection<Todo> = todos.forUser(userId)

    @PostMapping("/todos")
    fun saveTodo(@RequestBody todo: Todo): ResponseEntity<Todo> {
        val isNew = todo.id.isBlank();
        todos.save(todo)
        return if (isNew) ResponseEntity<Todo>(todo, HttpStatus.CREATED) else ResponseEntity.ok(todo)
    }
}