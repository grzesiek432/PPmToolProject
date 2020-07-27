package com.chojnacki.grzegorz.ppmtool.services;

import com.chojnacki.grzegorz.ppmtool.domain.Project;

public interface ProjectService {

    Project saveOrUpdate(Project project);

    Project findProjectByIdentifier(String projectId);

    Iterable<Project> findAllProjects();

    void deleteProjectByIdentifier(String projectId);


}
