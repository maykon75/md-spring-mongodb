package com.database.mongo.project.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    @Test
    void testNotFoundExceptionProperties() {

        NotFoundException exception = new NotFoundException("1", "test");

        assertEquals("1", exception.getId());
        assertEquals("test", exception.getMessage());
    }
}