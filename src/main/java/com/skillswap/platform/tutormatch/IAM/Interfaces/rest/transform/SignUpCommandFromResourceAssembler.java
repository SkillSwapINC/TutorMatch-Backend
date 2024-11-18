package com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform;



import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SignUpCommand;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Entities.Role;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.password(), roles);
    }
}