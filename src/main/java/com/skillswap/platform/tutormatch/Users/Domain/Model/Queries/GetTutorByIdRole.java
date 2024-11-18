package com.skillswap.platform.tutormatch.Users.Domain.Model.Queries;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;

public record GetTutorByIdRole(Long tutorId, RoleType roleType) {
}
