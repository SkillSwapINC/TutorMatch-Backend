package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.TutoringSessionResource;

/**
 * Assembles a resource representation of a tutoring session from the provided entity.
 */
public class TutoringSessionResourceFromEntityAssembler {

    /**
     * Converts a {@link TutoringSession} entity into a {@link TutoringSessionResource}.
     *
     * @param entity the entity representing the tutoring session
     * @return a resource representation of the tutoring session
     */
    public static TutoringSessionResource toResourceFromEntity(TutoringSession entity) {
        return new TutoringSessionResource(
                entity.getId(),
                entity.getCourseName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getTimes(),
                entity.getImage(),
                entity.getTutorId(),
                entity.getCourse().getId()
        );
    }
}
