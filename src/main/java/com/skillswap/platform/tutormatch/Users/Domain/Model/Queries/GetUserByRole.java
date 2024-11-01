package com.skillswap.platform.tutormatch.Users.Domain.Model.Queries;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;

public record GetUserByRole(RoleType roleType) {
}
