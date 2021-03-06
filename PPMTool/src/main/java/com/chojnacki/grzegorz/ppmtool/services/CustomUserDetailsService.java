package com.chojnacki.grzegorz.ppmtool.services;

import com.chojnacki.grzegorz.ppmtool.domain.User;
import com.chojnacki.grzegorz.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);


        if(user==null) new UsernameNotFoundException("User Not Found");
        return user;
    }

    @Transactional
    public User loadUserById(Long id)
    {
        User user = userRepository.getById(id);
        if(user==null) new UsernameNotFoundException("Username not found");
        return user;
    }
}
