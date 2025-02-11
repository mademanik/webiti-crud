package com.webiti.crud.controller;

import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.model.User;
import com.webiti.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User response = (User) authentication.getPrincipal();

        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_DATA_EMPTY.getMessage(), HttpStatus.NOT_FOUND, response);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Object> allUsers() {
        List <User> responses = userService.allUsers();
        return ResponseHandler.generateResponse(!responses.isEmpty() ? ApplicationMessages.USER_RETRIEVED.getMessage() :
                ApplicationMessages.USER_DATA_EMPTY.getMessage(), HttpStatus.OK, responses);
    }
}

