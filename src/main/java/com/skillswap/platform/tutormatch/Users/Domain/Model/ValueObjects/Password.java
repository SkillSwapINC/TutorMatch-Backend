package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;


import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {
    public Password(){this( null);}

    public Password {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }


}
