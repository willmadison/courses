import './App.css';
import { TodoList } from './TodoList';
import { Todos } from './Todos';
import React from 'react';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      todos: []
    }
  }

  refresh(todos) {
    this.setState({
      todos: todos
    })
  }

  render() {
    return (
      <div className="App">
        <div className="TodoSearch">
          <input type="text" id="userName" placeholder="Username" />

          <input type="button" value="Find Todo(s)" onClick={() => {
            const userId = document.getElementById("userName").value
            if (userId) {
              Todos.findFor(userId).then(
                todos => { this.refresh(todos) }
              );
            } else {
              window.alert("Please Enter a Username!")
            }
          }} />
        </div>

        <div className="TodoForm">
          <label>
            Name: &nbsp;
            <input type="text" id="todoUser" placeholder="UserName" />
          </label>
          <textarea id="todoContents" placeholder="Contents" />
          <input type="button" value="Save Todo" onClick={() => {
            const userId = document.getElementById("todoUser").value
            const contents = document.getElementById("todoContents").value

            if (userId && contents) {
              Todos.create(userId, contents).then(todo => {
                Todos.findFor(userId).then(
                  todos => { this.refresh(todos) }
                );
              });
            } else {
              window.alert("Please Enter a Username and Todo Content!")
            }
          }} />

        </div>


        { (this.state.todos.length > 0 ? <TodoList todos={this.state.todos} /> : <div />)}
      </div>
    );
  }
}

export default App;
