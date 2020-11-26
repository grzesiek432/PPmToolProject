package com.chojnacki.grzegorz.ppmtool.services;

import com.chojnacki.grzegorz.ppmtool.domain.Backlog;
import com.chojnacki.grzegorz.ppmtool.domain.ProjectTask;
import com.chojnacki.grzegorz.ppmtool.exceptions.ProjectNotFoundException;
import com.chojnacki.grzegorz.ppmtool.repositories.BacklogRepository;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectRepository;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;




    @Override
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask,String username) {

        try {
            //PTs to be added to a specific project, project != null Backlog exists
            Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();//backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set the backlog to the projectTask
            projectTask.setBacklog(backlog);
            // project sequence to be like this: IDPRO-1,IDPRO-2
            Integer backlogSequence = backlog.getPTSequence();
            //Update the Backlog sequence
            backlogSequence++;

            backlog.setPTSequence(backlogSequence);
            // Add Sequence to Project Task
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //INITIAL priority when priority null
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0) // In the future need projectTask.getPriority()== 0 to handle the form
            {
                projectTask.setPriority(3); // low priority
            }

            //INITIAL status when status is null or 0
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO-DO");
            }

            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }

    }

    @Override
    public Iterable<ProjectTask> findBacklogById(String id,String username) {

        projectService.findProjectByIdentifier(id,username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    @Override
    public ProjectTask findPTByProjectSequence(String backlog_id, String sequence,String username)
    {
        //make sure that we are searching on the right backlog
        projectService.findProjectByIdentifier(backlog_id,username);


        //make sure that task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if(projectTask == null)
        {
            throw new ProjectNotFoundException("Project Task '" +sequence+"' not found");
        }


        //make sure that backlog/project id in the path corresponds to the right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id))
        {
            throw new ProjectNotFoundException("Project Task '" + sequence + "' does not exist in project: '"+backlog_id);
        }


        return projectTask;
    }
    @Override
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id,String pt_id,String username) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);

        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);


    }

    @Override
    public void deletePTByProjectSequence(String backlog_id, String pt_id,String username) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);

        projectTaskRepository.delete(projectTask);

    }
}
