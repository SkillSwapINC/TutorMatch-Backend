package com.skillswap.platform.tutormatch.IAM.Interfaces.rest;


import com.skillswap.platform.tutormatch.IAM.Domain.Services.UserAuthCommandService;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.AuthenticatedUserAuthResource;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.SignInResource;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.SignUpResource;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.UserAuthResource;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform.AuthenticatedUserAuthResourceFromEntityAssembler;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserAuthCommandService userAuthCommandService;

    public AuthenticationController(UserAuthCommandService userAuthCommandService) {
        this.userAuthCommandService = userAuthCommandService;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserAuthResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUserAuth = userAuthCommandService.handle(signInCommand);
        if (authenticatedUserAuth.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserAuthResourceFromEntityAssembler.toResourceFromEntity(authenticatedUserAuth.get().getLeft(), authenticatedUserAuth.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param signUpResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserAuthResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var userAuth = userAuthCommandService.handle(signUpCommand);
        if (userAuth.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userAuthResource = UserAuthResourceFromEntityAssembler.toResourceFromEntity(userAuth.get());
        return new ResponseEntity<>(userAuthResource, HttpStatus.CREATED);

    }
}