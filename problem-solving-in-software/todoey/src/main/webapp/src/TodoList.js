import React from "react";

import { Todo } from './Todo';

export function TodoList(props) {
    const todos = props.todos;

    const todoItems = todos.map((todo) => {
        return <Todo key={todo.id} todo={todo} />
    })

    return (
        <div>
            <h2>TODO(s):</h2>
            <div className="TodoList">
                {todoItems}
            </div>
        </div>
    )
}