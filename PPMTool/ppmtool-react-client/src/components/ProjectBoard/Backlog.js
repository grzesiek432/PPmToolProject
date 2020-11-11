import React, { Component } from 'react'
import ProjectTask from './ProjectTasks/ProjectTask'

 class Backlog extends Component {
    render() {

        const {project_tasks_prop} = this.props;
        
        const tasks = project_tasks_prop.map((project_task)=>(
            <ProjectTask key={project_task.id} project_task={project_task} />
        ));
        return (
            <div class="container">
            <div class="row">
              <div class="col-md-4">
                <div class="card text-center mb-2">
                  <div class="card-header bg-secondary text-white">
                    <h3>TO DO</h3>
                  </div>
                </div>
               {tasks}
              </div>
              <div class="col-md-4">
                <div class="card text-center mb-2">
                  <div class="card-header bg-primary text-white">
                    <h3>In Progress</h3>
                  </div>
                </div>
               
              </div>
              <div class="col-md-4">
                <div class="card text-center mb-2">
                  <div class="card-header bg-success text-white">
                    <h3>Done</h3>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
        )
    }
}

export default Backlog;