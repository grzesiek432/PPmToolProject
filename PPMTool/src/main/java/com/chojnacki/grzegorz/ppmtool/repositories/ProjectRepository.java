package com.chojnacki.grzegorz.ppmtool.repositories;


import com.chojnacki.grzegorz.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();






}
