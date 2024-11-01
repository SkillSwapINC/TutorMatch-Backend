package com.skillswap.platform.tutormatch.Users.Application.Internal.commandservices;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Command.CreateUserCommand;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.EmailAddress;
import com.skillswap.platform.tutormatch.Users.Domain.Services.UserCommandService;
import com.skillswap.platform.tutormatch.Users.Infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for handling user-related commands.
 * Provides the implementation for creating and
 * managing users within the application.
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the creation of a new user based on the provided
     * {@code CreateUserCommand}.
     * Validates that the email in the command is unique
     * before proceeding with user creation.
     *
     * @param command the command containing the user's details needed for creation.
     * @return an {@code Optional<User>} containing the created user if successful, or an empty optional if not.
     * @throws IllegalArgumentException if a user with the specified email already exists.
     */
    @Override
    public Optional<User> handle(CreateUserCommand command) {
        var emailAddress = new EmailAddress(command.email());
        userRepository.findByEmail(emailAddress).ifPresent(user -> {
            throw new IllegalArgumentException("User with this email " + command.email() + " already exists");
        });
        var user = new User(command);
        userRepository.save(user);
        return Optional.of(user);

    }
}
