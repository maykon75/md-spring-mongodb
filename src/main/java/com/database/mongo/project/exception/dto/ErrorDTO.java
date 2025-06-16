package com.database.mongo.project.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private String message;
}
