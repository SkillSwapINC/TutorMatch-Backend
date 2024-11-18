package com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;

/**
 * Represents the resource for a user.
 */
public record UserResource(Long id,
                           String fullName,
                           String email,
                           String password,
                           String avatarUrl,
                           String gender,
                           int semester,
                           RoleType roleType,
                           Long tutorId) {}
