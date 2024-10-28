package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Avatar(String avatarUrl) {
    public Avatar {
        if (avatarUrl == null || avatarUrl.isBlank()) {
            throw new IllegalArgumentException("Avatar URL cannot be null or blank");
        }
    }
}
