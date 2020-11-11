import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../actions/backlogActions";

class ProjectBoard extends Component {
  //constructor to handle errors

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }
  render() {
    const { id } = this.props.match.params;
    const { project_tasks } = this.props.backlog;
    return (
      <div class="container">
        <Link to={`/addProjectTask/${id}`} class="btn btn-primary mb-3 mr-1">
          <i class="fa fa-plus-circle"> Create Project Task</i>
        </Link>
        <Link to={"/dashboard"} class="btn btn-primary mb-3">
          <i class="fa fa">
            <svg
              width="1em"
              height="1em"
              viewBox="0 0 16 16"
              class="bi bi-arrow-90deg-left"
              fill="currentColor"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fill-rule="evenodd"
                d="M1.146 4.854a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H12.5A2.5 2.5 0 0 1 15 6.5v8a.5.5 0 0 1-1 0v-8A1.5 1.5 0 0 0 12.5 5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4z"
              />
            </svg>
            Back to the DashBoard
          </i>
        </Link>

        <br />
        <hr />

        <Backlog project_tasks_prop={project_tasks} />
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
