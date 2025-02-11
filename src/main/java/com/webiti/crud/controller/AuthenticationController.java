package com.webiti.crud.controller;

import com.webiti.crud.dto.LoginResponse;
import com.webiti.crud.dto.LoginUserDto;
import com.webiti.crud.dto.RegisterUserDto;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.model.User;
import com.webiti.crud.service.AuthenticationService;
import com.webiti.crud.service.JwtService;
import com.webiti.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDto registerUserDto) {
        User response = authenticationService.signup(registerUserDto);
        return ResponseHandler.generateResponse(ApplicationMessages.REGISTER_SUCCESS.getMessage(), HttpStatus.OK, response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        LoginResponse response = LoginResponse.builder()
                .accessToken(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .refreshToken(refreshToken)
                .refreshTokenExpiresIn(jwtService.getRefreshTokenExpirationTime())
                .build();

        return ResponseHandler.generateResponse(ApplicationMessages.LOGIN_SUCCESS.getMessage(), HttpStatus.OK, response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refresh_token");

        try {
            String username = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String newAccessToken = jwtService.generateToken(userDetails);

                Map<String, String> response = new HashMap<>();
                response.put("access_token", newAccessToken);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
        }
    }


}
