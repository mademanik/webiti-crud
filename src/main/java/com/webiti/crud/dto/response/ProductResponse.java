package com.webiti.crud.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private Date createdAt;
    private Date updatedAt;
}
