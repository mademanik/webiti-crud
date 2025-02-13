package com.webiti.crud.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String fullName;
}
