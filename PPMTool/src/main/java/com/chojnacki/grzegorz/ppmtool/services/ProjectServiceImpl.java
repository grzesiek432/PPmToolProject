package com.chojnacki.grzegorz.ppmtool.services;


import com.chojnacki.grzegorz.ppmtool.domain.Project;
import com.chojnacki.grzegorz.ppmtool.exceptions.ProjectIdException;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public Project saveOrUpdate(Project project) {
       try {
           project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
           return projectRepository.save(project);
       } catch (Exception e) {
           throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists" );
       }




    }
}
