package com.webiti.crud.controller;

import com.webiti.crud.dto.request.RegisterUserDto;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.model.User;
import com.webiti.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Object> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        User response = userService.createAdministrator(registerUserDto);
        return ResponseHandler.generateResponse(ApplicationMessages.ADMIN_CREATED.getMessage(), HttpStatus.OK, response);
    }
}

