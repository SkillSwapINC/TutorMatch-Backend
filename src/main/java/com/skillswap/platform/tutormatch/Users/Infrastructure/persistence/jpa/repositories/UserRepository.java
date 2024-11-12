package com.skillswap.platform.tutormatch.Users.Infrastructure.persistence.jpa.repositories;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates.User;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.EmailAddress;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     *
     * @param email the {@link EmailAddress} of the user to be retrieved
     * @return an {@link Optional} containing the {@link User} if found,
     * or empty if no user matches the email
     */
    Optional<User> findByEmail(EmailAddress email);

    /**
     * Retrieves a list of users that have the specified role.
     *
     * @param roleType the {@link RoleType} to filter users by
     * @return a {@link List} of {@link User} objects that match the specified role
     */
    List<User> findByRoleRoleType(RoleType roleType);

    /**
     * Retrieves a user by their tutor ID.
     *
     * @param tutorId the tutor ID of the user to be retrieved
     * @return an {@link Optional} containing the {@link User} if found,
     * or empty if no user matches the tutor ID
     */
    Optional<User> findByTutorId(Long tutorId);

    /**
     * Retrieves the maximum tutor ID from the User table.
     *
     * @return an {@link Optional} containing the maximum tutor ID,
     * or an empty Optional if no users exist.
     */
    @Query("SELECT MAX(u.tutorId) FROM User u")
    Optional<Long> findMaxTutorId();
}
