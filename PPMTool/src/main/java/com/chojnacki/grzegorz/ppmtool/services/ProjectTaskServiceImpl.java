package com.chojnacki.grzegorz.ppmtool.services;

import com.chojnacki.grzegorz.ppmtool.domain.Backlog;
import com.chojnacki.grzegorz.ppmtool.domain.ProjectTask;
import com.chojnacki.grzegorz.ppmtool.repositories.BacklogRepository;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskServiceImpl implements ProjectTaskService{

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;


    @Override
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {


        //PTs to be added to a specific project, project != null Backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //set the backlog to the projectTask
        projectTask.setBacklog(backlog);
        // project sequence to be like this: IDPRO-1,IDPRO-2
        Integer backlogSequence = backlog.getPTSequence();
        //Update the Backlog sequence
        backlogSequence++;

        backlog.setPTSequence(backlogSequence);
        // Add Sequence to Project Task
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //INITIAL priority when priority null
        if(projectTask.getPriority()==null) // In the future need projectTask.getPriority()== 0 to handle the form
        {
            projectTask.setPriority(3); // low priority
        }

        //INITIAL status when status is null
        if(projectTask.getStatus()==""  || projectTask.getStatus()==null)
        {
            projectTask.setStatus("TO-DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    @Override
    public Iterable<ProjectTask> findBacklogById(String id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}
