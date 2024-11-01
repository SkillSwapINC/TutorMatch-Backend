package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Email String address) {
    public EmailAddress() { this(null); }
}
