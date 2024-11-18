/**
 * Get role by name query
 * <p>
 *     This class represents the query to get a role by its name.
 * </p>
 * @param name the name of the role
 * @see com.acme.center.platform.iam.domain.model.valueobjects.Roles
 */
package com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.ValueObjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}