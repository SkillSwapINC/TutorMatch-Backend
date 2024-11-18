package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.CourseResource;

/**
 * Assembles a resource representation of a course from the provided entity.
 */
public class CourseResourceFromEntityAssembler {

    /**
     * Converts a {@link Course} entity into a {@link CourseResource}.
     *
     * @param entity the entity representing the tutoring session
     * @return a resource representation of the tutoring session
     */
    public static CourseResource toResourceFromEntity(Course entity) {
        return new CourseResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCycle()
        );
    }
}
