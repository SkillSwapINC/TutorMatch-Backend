package com.skillswap.platform.tutormatch.IAM.Domain.Services;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SignInCommand;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * User command service
 * <p>
 *     This interface represents the service to handle user commands.
 * </p>
 */
public interface UserAuthCommandService {
    /**
     * Handle sign in command
     * @param command the {@link SignInCommand} command
     * @return an {@link Optional} of {@link ImmutablePair} of {@link UserAuth} and {@link String}
     */
    Optional<ImmutablePair<UserAuth, String>> handle(SignInCommand command);

    /**
     * Handle sign up command
     * @param command the {@link SignUpCommand} command
     * @return an {@link Optional} of {@link UserAuth} entity
     */
    Optional<UserAuth> handle(SignUpCommand command);
}