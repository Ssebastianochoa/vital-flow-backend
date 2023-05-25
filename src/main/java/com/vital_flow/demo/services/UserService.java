package com.vital_flow.demo.services;


import com.vital_flow.demo.model.User;
import com.vital_flow.demo.model.exceptions.BusinessException;

public interface UserService {

    User createUser(User user) throws BusinessException;

    User getUser(String email) throws BusinessException;
}
