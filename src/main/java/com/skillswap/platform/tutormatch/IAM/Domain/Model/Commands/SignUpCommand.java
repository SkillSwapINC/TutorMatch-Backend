/**
 * Sign up command
 * <p>
 *     This class represents the command to sign up a user.
 * </p>
 * @param username the username of the user
 * @param password the password of the user
 * @param roles the roles of the user
 *
 * @see com.acme.center.platform.iam.domain.model.aggregates.User
 */
package com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.Entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}