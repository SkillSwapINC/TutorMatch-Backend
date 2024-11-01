package com.skillswap.platform.tutormatch.Users.Domain.Model.Queries;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.EmailAddress;

public record GetUserByEmail(EmailAddress emailAddress) {
}
