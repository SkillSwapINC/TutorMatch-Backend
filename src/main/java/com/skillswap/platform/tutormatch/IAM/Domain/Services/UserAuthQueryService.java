package com.skillswap.platform.tutormatch.IAM.Domain.Services;



import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries.GetAllUsersAuthQuery;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries.GetUserAuthByIdQuery;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries.GetUserAuthByUsernameQuery;

import java.util.List;
import java.util.Optional;

/**
 * User query service
 * <p>
 *     This interface represents the service to handle user queries.
 * </p>
 */
public interface UserAuthQueryService {
    /**
     * Handle get all users query
     * @param query the {@link GetAllUsersAuthQuery} query
     * @return a list of {@link UserAuth} entities
     */
    List<UserAuth> handle(GetAllUsersAuthQuery query);

    /**
     * Handle get user by id query
     * @param query the {@link GetUserAuthByIdQuery} query
     * @return an {@link Optional} of {@link UserAuth} entity
     */
    Optional<UserAuth> handle(GetUserAuthByIdQuery query);

    /**
     * Handle get user by username query
     * @param query the {@link GetUserAuthByUsernameQuery} query
     * @return an {@link Optional} of {@link UserAuth} entity
     */
    Optional<UserAuth> handle(GetUserAuthByUsernameQuery query);
}