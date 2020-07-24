package com.chojnacki.grzegorz.ppmtool.services;


import com.chojnacki.grzegorz.ppmtool.domain.Project;
import com.chojnacki.grzegorz.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public Project saveOrUpdate(Project project) {
        //Logic



        return projectRepository.save(project);
    }
}
