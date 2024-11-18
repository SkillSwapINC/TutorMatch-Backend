package com.skillswap.platform.tutormatch.Users.Domain.Services;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling user-related queries,
 * such as retrieving users by role or email.
 */
public interface UserQueryService {
    /**
     * Retrieves a list of users that match the specified role.
     *
     * @param query the {@link GetUserByRole} query containing the role to filter users by
     * @return a {@link List} of {@link User} objects that have the specified role
     */
    List<User> handle(GetUserByRole query);

    /**
     * Retrieves a user by their email address.
     *
     * @param query the {@link GetUserByEmailPassword} query containing the email address to search for
     * @return an {@link Optional} containing the {@link User} if found,
     * or empty if no user matches the email and password
     */
    Optional<User> handle(GetUserByEmailPassword query);

    /**
     * Retrieves a user by their email address.
     *
     * @param query the {@link GetTutorByEmail} query containing the email address to search for
     * @return an {@link Optional} containing the {@link User} if found,
     * or empty if no user matches the email
     */

    Optional<User> handle(GetTutorByEmail query);

    /**
     * Retrieves a list of all users.
     *
     * @param query the {@link GetAllUsersQuery} query to fetch all users
     * @return a {@link List} of all {@link User} objects currently stored
     */
    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserById query);

    Optional<User> handle(GetTutorByIdRole query);
}
