package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command;

/**
 * Command to update a tutoring session
 * @param tutoringSessionId the tutoring session id.
 *                          Cannot be null or less than 1
 * @param description the tutoring session description.
 *                    Cannot be null or blank
 * @param price the tutoring session price.
 *              Cannot be null or negative
 *              Must be greater than 0
 *              Must be a valid number
 * @param image the tutoring session image.
 *              Cannot be null or blank
 */
public record UpdateTutoringCommand(Long tutoringSessionId, String description, Double price, String image) {

    /**
     * Constructor
     * @param tutoringSessionId the tutoring session id.
     *                          Cannot be null or less than 1
     * @param description the tutoring session description.
     *                    Cannot be null or blank
     * @param price the tutoring session price.
     *              Cannot be null or negative
     *              Must be greater than 0
     *              Must be a valid number
     * @param image the tutoring session image.
     *              Cannot be null or blank
     * @throws IllegalArgumentException if tutoringSessionId is null or less than 1
     * @throws IllegalArgumentException if description is null or blank
     * @throws IllegalArgumentException if price is null or negative
     * @throws IllegalArgumentException if price is less than 1
     */
    public UpdateTutoringCommand {
        if (tutoringSessionId == null || tutoringSessionId < 1) {
            throw new IllegalArgumentException("Tutoring session id cannot be null or less than 1");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image cannot be null or empty");
        }
    }
}
