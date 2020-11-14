package com.toccorp.problemsolving.todoey.todos

import com.fasterxml.jackson.annotation.JsonProperty

data class Todo(
    @JsonProperty("id") var id: String = "",
    @JsonProperty("userId") var userId: String,
    @JsonProperty("contents") var contents: String
)