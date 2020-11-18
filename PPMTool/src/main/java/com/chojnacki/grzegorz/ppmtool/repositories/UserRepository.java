package com.chojnacki.grzegorz.ppmtool.repositories;


import com.chojnacki.grzegorz.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {


}
