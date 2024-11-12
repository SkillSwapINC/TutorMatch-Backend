package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.CreateTutoringSessionCommand;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.CreateTutoringSessionResource;

/**
 * Assembles a command for creating a tutoring session from the provided resource.
 */
public class CreateTutoringSessionCommandFromResourceAssembler {

    /**
     * Converts a {@link CreateTutoringSessionResource} into a {@link CreateTutoringSessionCommand}.
     *
     * @param resource the resource containing the details for the tutoring session
     * @return a command that contains the data needed to create a tutoring session
     */
    public static CreateTutoringSessionCommand toCommandFromResource(CreateTutoringSessionResource resource) {
        return new CreateTutoringSessionCommand(
                resource.courseName(),
                resource.description(),
                resource.price(),
                resource.times(),
                resource.image(),
                resource.tutorId(),
                resource.courseId(),
                resource.course()

        );
    }
}
