package com.example.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id %d was not found", entityName, id));
    }
}