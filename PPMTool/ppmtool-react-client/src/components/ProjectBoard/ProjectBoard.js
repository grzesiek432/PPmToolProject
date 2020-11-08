import React, { Component } from "react";
import {Link} from "react-router-dom";

export default class ProjectBoard extends Component {
  render() {

    const {id} = this.props.match.params;
    return (
      <div class="container">
        <Link to={`/addProjectTask/${id}`} class="btn btn-primary mb-3">
          <i class="fa fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />

        <div class="container">
          <div class="row">
            <div class="col-md-4">
              <div class="card text-center mb-2">
                <div class="card-header bg-secondary text-white">
                  <h3>TO DO</h3>
                </div>
              </div>

              <div class="card mb-1 bg-light">
                <div class="card-header text-primary">
                  ID: projectSequence -- Priority: priorityString
                </div>
                <div class="card-body bg-light">
                  <h5 class="card-title">project_task.summary</h5>
                  <p class="card-text text-truncate ">
                    project_task.acceptanceCriteria
                  </p>
                  <a href="" class="btn btn-primary">
                    View / Update
                  </a>

                  <button class="btn btn-danger ml-4">Delete</button>
                </div>
              </div>
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
      </div>
    );
  }
}
