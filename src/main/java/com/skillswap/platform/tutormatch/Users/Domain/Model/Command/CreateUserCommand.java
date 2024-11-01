package com.skillswap.platform.tutormatch.Users.Domain.Model.Command;

import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;

/**
 * Command for creating a new user, encapsulating the required user details.
 * This command object is used to transfer data needed to create a {@code User}.
 *
 * @param firstName  the first name of the user
 * @param lastName   the last name of the user
 * @param email      the user's email address
 * @param password   the user's password
 * @param avatarUrl  the URL for the user's avatar image
 * @param gender     the gender of the user
 * @param semester   the current semester of the user
 * @param roleType   the role type of the user (e.g., student, teacher)
 */
public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        String avatarUrl,
        String gender,
        int semester,
        RoleType roleType ) {}
