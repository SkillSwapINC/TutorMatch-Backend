package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries;

/**
 * Represents a query to retrieve all tutoring sessions conducted by a specific tutor.
 *
 * @param tutorId The ID of the tutor whose tutoring sessions to retrieve.
 */
public record GetAllTutoringsByTutorId(Long tutorId) {
}
