package com.chojnacki.grzegorz.ppmtool.services;


import com.chojnacki.grzegorz.ppmtool.domain.User;
import com.chojnacki.grzegorz.ppmtool.exceptions.UsernameAlreadyExistsException;
import com.chojnacki.grzegorz.ppmtool.repositories.UserRepository;
import com.chojnacki.grzegorz.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //UserName has to be unique(exception)
            newUser.setUsername(newUser.getUsername());
            //Make sure that password and confirmPassword match
            // Do not persist or show the confirmPassword
            newUser.setConfirmPassword("");

            return userRepository.save(newUser);

        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }


    }
}
