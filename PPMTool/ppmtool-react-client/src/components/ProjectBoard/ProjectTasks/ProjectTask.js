import React, { Component } from 'react'

class ProjectTask extends Component {
    render() {

        const {project_task} = this.props;

        return (
            <div class="card mb-1 bg-light">
            <div class="card-header text-primary">
              ID: {project_task.projectSequence} -- Priority: {" "}
              {project_task.priority}
            </div>
            <div class="card-body bg-light">
              <h5 class="card-title">{project_task.summary}</h5>
              <p class="card-text text-truncate ">
               { project_task.acceptanceCriteria}
              </p>
              <a href="" class="btn btn-primary">
                View / Update
              </a>

              <button class="btn btn-danger ml-4">Delete</button>
            </div>
          </div>
        )
    }
}

export default ProjectTask;

