package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.UpdateTutoringCommand;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.UpdateTutoringSessionResource;

public class UpdateTutoringSessionCommandFromResourceAssembler {
    public static UpdateTutoringCommand toCommand(Long tutoringSessionId, UpdateTutoringSessionResource resource) {
        return new UpdateTutoringCommand(tutoringSessionId, resource.description(), resource.price(), resource.image());
    }
}
