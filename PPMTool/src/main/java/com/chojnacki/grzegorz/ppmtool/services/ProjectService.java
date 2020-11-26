package com.chojnacki.grzegorz.ppmtool.services;

import com.chojnacki.grzegorz.ppmtool.domain.Project;

public interface ProjectService {

    Project saveOrUpdate(Project project,String username);

    Project findProjectByIdentifier(String projectId, String username);

    Iterable<Project> findAllProjects(String username);

    void deleteProjectByIdentifier(String projectId,String username);


}
