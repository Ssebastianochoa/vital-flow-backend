package com.vital_flow.demo.services;


import java.util.List;
import java.util.Optional;

import com.vital_flow.demo.model.User;
import com.vital_flow.demo.model.exceptions.BusinessException;
import com.vital_flow.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //logica de negocio, validaciones, restricciones, excepciones.
    @Override
    public User createUser(User user) throws BusinessException {
        User validateUser = userRepository.findByEmail(user.getEmail());
        if (validateUser != null && user.getEmail().equals(validateUser.getEmail())) {
            throw new BusinessException("El email: " + user.getEmail() + ", ya existe");
        }
        if (validateUser == null) {
            //La longitud y caracteres especiales de la contraseña. (Opcional)
            if (user.getUsername() == null || user.getUsername().isBlank()) {
                throw new BusinessException("Username is not valid");
            }
            if (user.getEmail() == null || user.getEmail().isBlank()) {
                throw new BusinessException("Email is not valid");
            }
            if (user.getPassword() == null || user.getPassword().isBlank()) {
                throw new BusinessException("Password is not valid");
            }
            //QUE NO EXISTA EL USUARIO CON EL MISMO CORREO (NECESITAMOS UN METODO QUE NOS DEVUELVA TODOS LOS USUARIOS O CORREOS DE USUARIOS YA CREADOS
            //EN LA BASE DE DATOS.
            validateEmail(user);
        }
        return userRepository.save(user);
    };

    public User getUser(String email) throws BusinessException {
        if(email == null || email.isBlank()) {
            throw new BusinessException("Email is not valid");
        }
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if(userOptional.isEmpty()){
            throw new BusinessException("User with email " + email + " doesn't existe");
        }
        return userRepository.findByEmail(email);
    }

    public void updateUser(Long userId, boolean estado) throws BusinessException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new BusinessException("User with ID" + userId + "doesn't exits in the system");
        }
        User user = userOptional.get();
        user.setActive(estado);
        userRepository.save(user);
    }


    private void validateEmail(User user) throws BusinessException {
        List<User> databaseUsers = userRepository.findAll();
        for(User databaseUser : databaseUsers) {
            if(databaseUser.getEmail().equalsIgnoreCase(user.getEmail())){
                throw new BusinessException("Email already exists");
            }
        }
    }
}
