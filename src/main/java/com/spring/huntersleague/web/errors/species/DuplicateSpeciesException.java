package com.spring.huntersleague.web.errors.species;

public class DuplicateSpeciesException extends RuntimeException {
    public DuplicateSpeciesException(String message) {
        super(message);
    }
}
