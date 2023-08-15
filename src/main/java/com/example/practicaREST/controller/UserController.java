package com.example.practicaREST.controller;

import com.example.practicaREST.exception.RequestException;
import com.example.practicaREST.model.User;
import com.example.practicaREST.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        User user = userService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user){
        if (user.getName().equals("")|| user.getName() == null){
            throw new RuntimeException("name is required");
        }else if (user.getLastName().equals("")|| user.getLastName() == null){
            throw new RequestException("P-400",HttpStatus.BAD_REQUEST,"user last name is required");
        }else if (user.getEmail().equals("")|| user.getEmail() == null){
            throw new RequestException("P-400",HttpStatus.BAD_REQUEST,"user email is required");
        }
        userService.saveUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try {
            User userToDelete = userService.findById(id);
            userService.deleteUser(userToDelete.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new RequestException("P-404",HttpStatus.NOT_FOUND,"Id no encontrado para eliminar");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity refreshUser(@PathVariable Integer id,@RequestBody User user){
        try{
            User updatedUser = userService.findById(id);
            updatedUser.setName(user.getName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            userService.saveUser(updatedUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            throw new RequestException("P-404",HttpStatus.NOT_FOUND,"Id no encontrado para actualizar");
        }
    }


}
