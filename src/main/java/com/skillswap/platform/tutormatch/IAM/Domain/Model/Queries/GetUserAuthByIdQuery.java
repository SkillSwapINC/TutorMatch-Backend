/**
 * Get user by id query
 * <p>
 *     This class represents the query to get a user by its id.
 * </p>
 * @param userId the id of the user
 */
package com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries;

public record GetUserAuthByIdQuery(Long userAuthId) {
}
