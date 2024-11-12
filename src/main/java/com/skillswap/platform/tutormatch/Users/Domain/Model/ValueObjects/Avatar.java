package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

@Embeddable
public record Avatar(@Lob String avatarUrl) {
    public Avatar {
        if (avatarUrl == null || avatarUrl.isBlank()) {
            throw new IllegalArgumentException("Avatar URL cannot be null or blank");
        }
    }
}
