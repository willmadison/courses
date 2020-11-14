import React from "react";

export function Todo(props) {
    const todo = props.todo;

    return (
        <div className="Todo">
            <div id="contents">{todo.contents}</div>
        </div>
    );
}