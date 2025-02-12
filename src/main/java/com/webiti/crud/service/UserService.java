package com.webiti.crud.service;

import com.webiti.crud.dto.request.RegisterUserDto;
import com.webiti.crud.dto.request.UserRequest;
import com.webiti.crud.dto.response.UserResponse;
import com.webiti.crud.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> allUsers(int page, int size);
    User createAdministrator(RegisterUserDto input);
    UserResponse createUser(UserRequest input);
    UserResponse updateUserById(Integer id, UserRequest input);
    UserResponse getUserById(Integer id);
    void deleteUserById(Integer id);
}
