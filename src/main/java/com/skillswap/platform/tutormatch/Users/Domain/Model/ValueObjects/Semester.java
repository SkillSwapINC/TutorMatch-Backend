package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Semester(int semester) {
    public Semester {
        if (semester < 1) {
            throw new IllegalArgumentException("Semester must be a positive number");
        }
    }
}

