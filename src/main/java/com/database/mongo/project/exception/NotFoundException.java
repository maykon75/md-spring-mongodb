package com.database.mongo.project.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private final String id;

    public NotFoundException(String id, String message) {
        super(message);
        this.id = id;
    }

}
