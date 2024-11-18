package com.skillswap.platform.tutormatch.Users.Interfaces.rest;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Queries.*;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.EmailAddress;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.Password;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.RoleType;
import com.skillswap.platform.tutormatch.Users.Domain.Services.UserCommandService;
import com.skillswap.platform.tutormatch.Users.Domain.Services.UserQueryService;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources.CreateUserResource;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources.UpdateUserResource;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.resources.UserResource;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.skillswap.platform.tutormatch.Users.Interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing users.
 * Provides endpoints for creating, retrieving, and managing user data.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    /**
     * Constructs a UsersController with the specified UserCommandService and UserQueryService.
     *
     * @param userCommandService the service for handling user commands
     * @param userQueryService   the service for handling user queries
     */
    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a new user with the provided data.
     *
     * @param resource the resource containing user data for creation
     * @return a ResponseEntity containing the created UserResource or a bad request response
     */
    @Operation(
            summary = "Create a User",
            description = "Creates a user with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/users")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(createUserCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }



    /**
     * Retrieves users by their role (e.g., 'TEACHER' or 'STUDENT').
     *
     * @param role the role of the users to retrieve
     * @return a ResponseEntity containing a list of UserResource if found, or a bad request/not found response
     */
    @Operation(
            summary = "Get Users by Role",
            description = "Retrieves users by their role (e.g., TEACHER or STUDENT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "404", description = "No users found for the specified role")
    })
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<UserResource>> getUsersByRole(@PathVariable String role) {
        try {
            RoleType roleType = RoleType.valueOf(role.toLowerCase());
            var getUserByRoleQuery = new GetUserByRole(roleType);
            var users = userQueryService.handle(getUserByRoleQuery);

            if (users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var userResources = users.stream()
                    .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * Retrieves users by their email and password.
     *
     * @param email the email of the user to retrieve
     * @param password the password of the user to retrieve
     * @return a ResponseEntity containing the UserResource if found, or a not found response
     */
    @Operation(
            summary = "Get Users by Email and Password",
            description = "Retrieves users by their email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Long tutorId,
            @RequestParam(required = false) RoleType roleType) {

        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            var getUserByEmailPasswordQuery = new GetUserByEmailPassword(new EmailAddress(email), new Password(password));
            var user = userQueryService.handle(getUserByEmailPasswordQuery);
            if (user.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
            return ResponseEntity.ok(userResource);
        }
        else if (email != null && !email.isEmpty()) {
            var getTutorByEmailQuery = new GetTutorByEmail(new EmailAddress(email));
            var tutor = userQueryService.handle(getTutorByEmailQuery);
            if (tutor.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var tutorResource = UserResourceFromEntityAssembler.toResourceFromEntity(tutor.get());
            return ResponseEntity.ok(tutorResource);
        }
        else if (tutorId != null && roleType != null) {
            var getTutorByIdRoleQuery = new GetTutorByIdRole(tutorId, roleType);
            var tutor = userQueryService.handle(getTutorByIdRoleQuery);
            if (tutor.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var tutorResource = UserResourceFromEntityAssembler.toResourceFromEntity(tutor.get());
            return ResponseEntity.ok(tutorResource);
        }
        else if (email == null && password == null) {
            var getAllUsersQuery = new GetAllUsersQuery();
            var users = userQueryService.handle(getAllUsersQuery);
            var userResources = users.stream()
                    .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userResources);
        }
        else {
            return ResponseEntity.badRequest().body("Both email and password are required to search for a specific user.");
        }
    }



    /**
     * Updates a user.
     *
     * @param userId the id of the user to be updated
     * @param updateUserResource the resource containing the data for the user to be updated
     * @return the updated user resource
     */
    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource updateUserResource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(userId, updateUserResource);
        var updatedUser = userCommandService.handle(updateUserCommand);

        if (updatedUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResource> getUser(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserById(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
}

