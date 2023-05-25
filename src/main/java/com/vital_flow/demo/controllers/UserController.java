package com.vital_flow.demo.controllers;

import com.vital_flow.demo.model.User;
import com.vital_flow.demo.model.exceptions.BusinessException;
import com.vital_flow.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
    @PostMapping
    public User createdUser(@RequestBody User user) {
        try {
            return userService.createUser(user);
        } catch (BusinessException e) {
            //AQUI DEBEMOS RESPONDER UN CODIGO 400 + EL MENSAJE DE LA EXCEPTION.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) throws BusinessException {
        User userFound = userService.getUser(email);
        if(userFound == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return userFound;
    }
}
