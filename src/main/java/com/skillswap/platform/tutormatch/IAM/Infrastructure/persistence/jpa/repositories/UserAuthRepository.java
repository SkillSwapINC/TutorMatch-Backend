package com.skillswap.platform.tutormatch.IAM.Infrastructure.persistence.jpa.repositories;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the User entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long>
{
    /**
     * This method is responsible for finding the user by username.
     * @param username The username.
     * @return The userAuth object.
     */
    Optional<UserAuth> findByUsername(String username);

    /**
     * This method is responsible for checking if the userAuth exists by username.
     * @param username The username.
     * @return True if the userAuth exists, false otherwise.
     */
    boolean existsByUsername(String username);
}