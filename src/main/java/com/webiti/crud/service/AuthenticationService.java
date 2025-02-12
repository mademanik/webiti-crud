package com.webiti.crud.service;

import com.webiti.crud.dto.request.LoginUserDto;
import com.webiti.crud.dto.request.RegisterUserDto;
import com.webiti.crud.model.User;

public interface AuthenticationService {
    User signup(RegisterUserDto input);
    User authenticate(LoginUserDto input);
}
