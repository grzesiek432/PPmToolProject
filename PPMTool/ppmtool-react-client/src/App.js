import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";
import ProjectTaskView from "./components/ProjectBoard/ProjectTasks/ProjectTaskView";



function App() {
  document.body.style = "background-color: lemonchiffon";
  return (
    <Provider store={store} className="provider">
      <Router>
        <div className="App">
          <Header />
          <Route
            exact
            path="/dashboard"
            component={Dashboard}
            className="dashboard"
          />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
          <Route exact path="/projectBoard/:id" component={ProjectBoard} />
          <Route exact path="/addProjectTask/:id" component={AddProjectTask} />
          <Route exact path="/updateTask/:backlog_id/:pt_id" component={UpdateProjectTask} />
          <Route exact path="/projectTaskView/:backlog_id/:pt_id" component={ProjectTaskView}/>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
