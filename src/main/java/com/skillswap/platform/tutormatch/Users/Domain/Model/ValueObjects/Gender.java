package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Gender(String gender) {
    public Gender {
        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("Gender cannot be null or blank");
        }

        if (!gender.equalsIgnoreCase("male") &&
                !gender.equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("Invalid gender value");
        }
    }
}

