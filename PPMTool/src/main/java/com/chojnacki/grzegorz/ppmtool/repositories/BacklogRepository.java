package com.chojnacki.grzegorz.ppmtool.repositories;


import com.chojnacki.grzegorz.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String identifier);

}
