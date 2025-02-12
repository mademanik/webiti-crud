package com.webiti.crud.dto.request;

import com.webiti.crud.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String fullName;
}
