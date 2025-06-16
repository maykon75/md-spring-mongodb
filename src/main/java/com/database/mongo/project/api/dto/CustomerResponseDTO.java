package com.database.mongo.project.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponseDTO {
    private String id;
    private String name;
    private int age;
    private String email;
    private String cellPhone;
}
