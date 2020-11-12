package com.toccorp.problemsolving.todoey

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoeyApplication

fun main(args: Array<String>) {
	runApplication<TodoeyApplication>(*args)
}
