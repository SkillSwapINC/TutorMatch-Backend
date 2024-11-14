package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command;

/**
 * Command to delete a tutoring session
 * @param tutoringId the tutoring session ID
 *                   Cannot be null or less than 1
 *
 */
public record DeleteTutoringCommand(Long tutoringId) {
    /**
     * Constructor
     * @param tutoringId the tutoring session ID
     *                   Cannot be null or less than 1
     * @throws IllegalArgumentException if tutoringId is null or less than 1
     */


    public DeleteTutoringCommand {
        if (tutoringId == null || tutoringId <= 0) {
            throw new IllegalArgumentException("Tutoring ID cannot be null or less than 1");
        }
    }
}
