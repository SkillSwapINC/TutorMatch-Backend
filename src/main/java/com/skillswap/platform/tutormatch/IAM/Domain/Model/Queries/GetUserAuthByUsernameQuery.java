/**
 * Get user by username query
 * <p>
 *     This class represents the query to get a user by its username.
 * </p>
 * @param username the username of the user
 */
package com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries;

public record GetUserAuthByUsernameQuery(String username) {
}