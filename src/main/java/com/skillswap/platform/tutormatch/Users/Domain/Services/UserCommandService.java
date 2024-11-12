package com.skillswap.platform.tutormatch.Users.Domain.Services;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Command.CreateUserCommand;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Command.UpdateUserCommand;

import java.util.Optional;

/**
 * Service interface for handling user-related commands, such as creating a new user.
 */
public interface UserCommandService {

    /**
     * Processes the creation of a new user based on the provided command data.
     *
     * @param command the {@link CreateUserCommand} containing details for the new user
     * @return an {@link Optional} containing the created {@link User} if successful, or empty if not
     */
    Optional<User> handle(CreateUserCommand command);

    /**
     * Updates an existing user based on the provided command data.
     *
     * <p>This method processes the update request and returns an {@link Optional} containing the updated
     * {@link User} if the operation is successful.
     * If the update fails or the user is not found, an empty {@link Optional} is returned.
     *
     */
    Optional<User> handle(UpdateUserCommand command);
}
