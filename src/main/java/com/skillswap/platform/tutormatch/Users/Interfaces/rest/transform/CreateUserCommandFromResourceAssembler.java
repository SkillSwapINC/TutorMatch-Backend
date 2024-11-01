package com.skillswap.platform.tutormatch.Users.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Command.CreateUserCommand;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources.CreateUserResource;

/**
 * Assembles a {@link CreateUserCommand} from a {@link CreateUserResource}.
 */
public class CreateUserCommandFromResourceAssembler {

    /**
     * Converts a {@link CreateUserResource} to a {@link CreateUserCommand}.
     *
     * @param resource the {@link CreateUserResource} containing user data
     * @return a {@link CreateUserCommand} populated with the data from the resource
     */
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.password(),
                resource.avatarUrl(),
                resource.gender(),
                resource.semester(),
                resource.roleType()

        );
    }
}
