package com.example.practicaREST.service;

import com.example.practicaREST.model.User;
import com.example.practicaREST.repository.iUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private iUserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id).get();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }


}
