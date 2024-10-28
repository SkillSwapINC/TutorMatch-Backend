package com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;

/**
 * Represents the resource for creating a new user.
 */
public record CreateUserResource(
        String firstName,
        String lastName,
        String email,
        String password,
        String avatarUrl,
        String gender,
        int semester,
        RoleType roleType ) {}
