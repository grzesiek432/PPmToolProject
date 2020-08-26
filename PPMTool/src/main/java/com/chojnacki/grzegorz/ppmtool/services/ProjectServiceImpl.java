package com.chojnacki.grzegorz.ppmtool.services;


import com.chojnacki.grzegorz.ppmtool.domain.Backlog;
import com.chojnacki.grzegorz.ppmtool.domain.Project;
import com.chojnacki.grzegorz.ppmtool.exceptions.ProjectIdException;
import com.chojnacki.grzegorz.ppmtool.repositories.BacklogRepository;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;




    @Override
    public Project saveOrUpdate(Project project) {
       try {
           project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

           if(project.getId()==null)
           {
               Backlog backlog = new Backlog();
               project.setBacklog(backlog);
               backlog.setProject(project);
               backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
           }

           if(project.getId() != null)
           {
               project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
           }

           return projectRepository.save(project);

       } catch (Exception e) {
           throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists" );
       }

    }

    @Override
    public Project findProjectByIdentifier(String projectId) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null)
        {
            throw new ProjectIdException("Project ID '"+projectId+"' doesn't exist!!!");
        }

        return project;
     }

     @Override
    public Iterable<Project> findAllProjects()
     {
         return projectRepository.findAll();
     }

     @Override
    public void deleteProjectByIdentifier(String projectId)
     {

         Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
         if(project == null)
         {
             throw new ProjectIdException("Cannot Project with '"+projectId+"'. This project does not exist.");
         }

         projectRepository.delete(project);
     }

}
