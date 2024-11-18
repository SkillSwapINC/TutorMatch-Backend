package com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.AuthenticatedUserAuthResource;

public class AuthenticatedUserAuthResourceFromEntityAssembler {
    public static AuthenticatedUserAuthResource toResourceFromEntity(UserAuth userAuth, String token) {
        return new AuthenticatedUserAuthResource(userAuth.getId(), userAuth.getUsername(), token);
    }
}