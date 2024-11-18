package com.skillswap.platform.tutormatch.Users.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources.UserResource;

/**
 * Assembles a {@link UserResource} from a {@link User} entity.
 */
public class UserResourceFromEntityAssembler {

    /**
     * Converts a {@link User} entity to a {@link UserResource}.
     *
     * @param entity the {@link User} entity to convert
     * @return a {@link UserResource} populated with data from the user entity
     */
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getFullName(),
                entity.getEmailAddress(),
                entity.getPassword(),
                entity.getAvatarUrl(),
                entity.getGender(),
                entity.getSemester(),
                entity.getRoleType(),
                entity.getTutorId()
        );
    }
}

