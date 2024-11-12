package com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources;

/**
 * Represents a resource for updating a user's attributes.
 *
 * <p>This record class encapsulates the data required to update a user's avatar URL, gender, and semester.
 *
 * @param avatarUrl the new avatar URL for the user
 * @param gender the new gender of the user
 * @param semester the new semester of the user
 */
public record UpdateUserResource(String avatarUrl, String gender, int semester) {
}
