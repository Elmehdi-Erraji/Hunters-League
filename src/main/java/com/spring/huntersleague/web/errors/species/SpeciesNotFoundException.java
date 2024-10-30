package com.spring.huntersleague.web.errors.species;

public class SpeciesNotFoundException extends RuntimeException {
    public SpeciesNotFoundException(String message) {
        super(message);
    }
}
