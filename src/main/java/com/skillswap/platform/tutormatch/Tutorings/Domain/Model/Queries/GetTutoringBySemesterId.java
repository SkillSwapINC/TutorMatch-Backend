package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries;

/**
 * Represents a query to retrieve tutoring sessions associated with a specific semester.
 *
 * @param cycle The cycle number of the semester.
 */
public record GetTutoringBySemesterId(int cycle) {
}
