import React from 'react';
import {Link} from "react-router-dom"

const CreateProjectButton = () => {
    
        return (

            <React.Fragment>
                <Link to="/addProject" className="btn btn-lg btn-info ml-3">
                Create Project


                </Link>

            </React.Fragment>
        ); 
    };


export default CreateProjectButton;
