package com.example.bookstore.persistence.jpa.exeption;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, String identity) {
        super(entityName + " with identity " + identity + " not found");
    }

}
