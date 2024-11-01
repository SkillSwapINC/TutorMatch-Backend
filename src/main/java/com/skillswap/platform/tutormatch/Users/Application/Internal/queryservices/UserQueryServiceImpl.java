package com.skillswap.platform.tutormatch.Users.Application.Internal.queryservices;


import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Queries.GetAllUsersQuery;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Queries.GetUserByEmail;
import com.skillswap.platform.tutormatch.Users.Domain.Model.Queries.GetUserByRole;
import com.skillswap.platform.tutormatch.Users.Domain.Services.UserQueryService;
import com.skillswap.platform.tutormatch.Users.Infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling user-related queries.
 * Provides methods to retrieve users by email, role, or retrieve all users.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user based on their email address.
     *
     * @param query the {@code GetUserByEmail} query containing the email address to search.
     * @return an {@code Optional<User>} with the user if found, or an empty optional if not found.
     */
    @Override
    public Optional<User> handle(GetUserByEmail query){
        return userRepository.findByEmail(query.emailAddress());
    }

    /**
     * Retrieves a list of users with a specified role.
     *
     * @param query the {@code GetUserByRole} query containing the role type to search for.
     * @return a {@code List<User>} containing users with the specified role,
     * or an empty list if none found.
     */
    @Override
    public List<User> handle(GetUserByRole query) {
        return userRepository.findByRoleRoleType(query.roleType());
    }

    /**
     * Retrieves all users currently stored in the system.
     *
     * @param query an empty {@code GetAllUsersQuery} indicating retrieval of all users.
     * @return a {@code List<User>} containing all users, or an empty list if no users are found.
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
}
