package com.skillswap.platform.tutormatch.Users.Domain.Model.Queries;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.EmailAddress;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.Password;

public record GetUserByEmailPassword(EmailAddress emailAddress, Password password) {
}
