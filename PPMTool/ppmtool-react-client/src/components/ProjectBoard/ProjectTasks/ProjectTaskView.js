import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { getProjectTask } from "../../../actions/backlogActions";
import Moment from "moment";

class ProjectTaskView extends React.Component {
  constructor() {
    super();

    this.state = {
      id: "",
      projectSequence: "",
      summary: "",
      acceptanceCriteria: "",
      status: "",
      priority: "",
      dueDate: "",
      created_At: "",
      updated_At: "",
      projectIdentifier: "",
      updateModalShow: false,
    };
  }

  componentDidMount() {
    const { backlog_id, pt_id } = this.props.match.params;
    this.props.getProjectTask(backlog_id, pt_id, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    const {
      id,
      projectSequence,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      created_At,
      updated_At,
      projectIdentifier,
    } = nextProps.project_task;

    this.setState({
      id,
      projectSequence,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      created_At,
      updated_At,
      projectIdentifier,
    });
  }

  render() {
    const { project_task } = this.props;
    let updateModalClose = () => this.setState({ updateModalShow: false });

    let convertDateUpdate;
    let priorityString;

    if (this.state.updated_At != null) {
      convertDateUpdate = Moment(this.state.updated_At).format("DD-MM-YYYY");
    }
    if (this.state.updated_At === null) {
      convertDateUpdate = "No Changes";
    }

    if (project_task.priority === 1) {
      priorityString = "HIGH";
    }
    if (project_task.priority === 2) {
      priorityString = "MEDIUM";
    }
    if (project_task.priority === 3) {
      priorityString = "LOW";
    }

    return (
      <div className="view-project">
        <div className="container">
          <div className="row">
            <div className="col-md-10 m-auto">
              <Link to={`/projectBoard/${this.state.projectIdentifier}`}>
                <input
                  className="btn btn-info mt-3"
                  value="Back to Project Board"
                />
              </Link>
              <hr />
              <h4 className="display-4 text-center">Project Task Details</h4>
              <hr />
              <form>
                <ul class="list-group mt-4">
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Project ID
                    <span class="badge badge-dark badge-pill">
                      {this.state.projectIdentifier}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Project Task ID
                    <span class="badge badge-dark badge-pill">
                      {this.state.projectSequence}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Summary
                    <span class="badge badge-dark badge-pill">
                      {this.state.summary}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Status
                    <span class="badge badge-dark badge-pill">
                      {this.state.status}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Priority
                    <span class="badge badge-dark badge-pill">
                      {priorityString}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Acceptance Criteria
                    <span class="badge badge-dark badge-pill">
                      {this.state.acceptanceCriteria}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Due Date
                    <span class="badge badge-dark badge-pill">
                      {Moment(this.state.dueDate).format("DD-MM-YYYY")}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Create Date
                    <span class="badge badge-dark badge-pill">
                      {Moment(this.state.created_At).format("DD-MM-YYYY")}
                    </span>
                  </li>
                  <li class="list-group-item d-flex justify-content-between align-items-center">
                    Update Date
                    <span class="badge badge-dark badge-pill">
                      {convertDateUpdate}
                    </span>
                  </li>
                </ul>

                <Link
                  to={`/updateTask/${this.state.projectIdentifier}/${this.state.projectSequence}`}
                >
                  <input
                    className="btn btn-primary btn-block"
                    value="Update Task"
                  />
                </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

ProjectTaskView.propTypes = {
  getProjectTask: PropTypes.func.isRequired,
  project_task: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  project_task: state.backlog.project_task,
});
export default connect(mapStateToProps, { getProjectTask })(ProjectTaskView);
