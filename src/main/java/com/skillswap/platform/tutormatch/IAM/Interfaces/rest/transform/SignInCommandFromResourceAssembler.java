package com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SignInCommand;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}