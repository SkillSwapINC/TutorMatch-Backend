package com.skillswap.platform.tutormatch.Users.Domain.Model.Command;

/**
 * Represents a command to update a user's attributes.
 *
 * <p>This record class encapsulates the necessary information to update a user's avatar URL, gender, and semester.
 * It ensures that the provided attributes are valid by throwing an `IllegalArgumentException` for invalid input.
 *
 * @param id the unique identifier of the user to be updated
 * @param avatarUrl the new avatar URL for the user
 * @param gender the new gender of the user
 * @param semester the new semester of the user
 */
public record UpdateUserCommand(Long id, String avatarUrl, String gender, int semester) {
    public UpdateUserCommand {
        if (avatarUrl == null || avatarUrl.isBlank()) {
            throw new IllegalArgumentException("Avatar URL cannot be null or blank");
        }
        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("Gender cannot be null or blank");
        }
        if (semester <= 0) {
            throw new IllegalArgumentException("Semester must be greater than 0");
        }
    }
}