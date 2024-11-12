package com.skillswap.platform.tutormatch.Users.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Command.UpdateUserCommand;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources.UpdateUserResource;

/**
 * Converts an `UpdateUserResource` to an `UpdateUserCommand`.
 *
 * <p>This static method extracts the necessary information from the provided `UpdateUserResource` and creates a new `UpdateUserCommand` object,
 * associating it with the given `userId`.
 *
 */
public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(id, resource.avatarUrl(), resource.gender(), resource.semester());
    }
}
