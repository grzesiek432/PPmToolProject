package com.chojnacki.grzegorz.ppmtool.services;


import com.chojnacki.grzegorz.ppmtool.domain.Backlog;
import com.chojnacki.grzegorz.ppmtool.domain.Project;
import com.chojnacki.grzegorz.ppmtool.domain.User;
import com.chojnacki.grzegorz.ppmtool.exceptions.ProjectIdException;
import com.chojnacki.grzegorz.ppmtool.exceptions.ProjectNotFoundException;
import com.chojnacki.grzegorz.ppmtool.repositories.BacklogRepository;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectRepository;
import com.chojnacki.grzegorz.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;




    @Override
    public Project saveOrUpdate(Project project,String username) {
       try {
           User user = userRepository.findByUsername(username);

           project.setUser(user);
           project.setProjectLeader(user.getUsername());
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
    public Project findProjectByIdentifier(String projectId, String username) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null)
        {
            throw new ProjectIdException("Project ID '"+projectId+"' doesn't exist!!!");
        }

        if(!project.getProjectLeader().equals(username))
        {
            throw new ProjectNotFoundException("Project not found in your account");
        }



        return project;
     }

     @Override
    public Iterable<Project> findAllProjects(String username)
     {
         return projectRepository.findAllByProjectLeader(username);
     }

     @Override
    public void deleteProjectByIdentifier(String projectId,String username)
     {

         projectRepository.delete(findProjectByIdentifier(projectId,username));
     }

}
