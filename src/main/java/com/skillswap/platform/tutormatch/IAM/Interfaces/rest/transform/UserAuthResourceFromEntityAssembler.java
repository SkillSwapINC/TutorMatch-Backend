package com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform;

import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Entities.Role;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.UserAuthResource;

public class UserAuthResourceFromEntityAssembler {
    public static UserAuthResource toResourceFromEntity(UserAuth userAuth) {
        var roles = userAuth.getRoles().stream().map(Role::getStringName).toList();
        return new UserAuthResource(userAuth.getId(), userAuth.getUsername(), roles);
    }
}