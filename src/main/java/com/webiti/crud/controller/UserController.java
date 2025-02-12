package com.webiti.crud.controller;

import com.webiti.crud.dto.response.UserResponse;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.model.User;
import com.webiti.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Object> allUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<User> userPage = userService.allUsers(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        return ResponseHandler.generateResponse(
                !userPage.isEmpty() ? ApplicationMessages.USER_RETRIEVED.getMessage() : ApplicationMessages.USER_DATA_EMPTY.getMessage(),
                HttpStatus.OK,
                response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Integer id) {
        UserResponse response = userService.getUserById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.USER_DELETED.getMessage(), HttpStatus.OK, null);
    }

}

