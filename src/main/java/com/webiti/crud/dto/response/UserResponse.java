package com.webiti.crud.dto.response;

import com.webiti.crud.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserResponse {
    private Integer id;
    private String fullName;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private Role role;
}
