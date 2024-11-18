package com.skillswap.platform.tutormatch.IAM.Interfaces.rest;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries.GetAllUsersAuthQuery;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Queries.GetUserAuthByIdQuery;
import com.skillswap.platform.tutormatch.IAM.Domain.Services.UserAuthQueryService;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.resources.UserAuthResource;
import com.skillswap.platform.tutormatch.IAM.Interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@RestController
@RequestMapping(value = "/api/v1/usersAuth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "UsersAuth", description = "UserAuth Management Endpoints")
public class UsersAuthController {
    private final UserAuthQueryService userAuthQueryService;

    public UsersAuthController(UserAuthQueryService userAuthQueryService) {
        this.userAuthQueryService = userAuthQueryService;
    }

    /**
     * This method returns all the users.
     * @return a list of user resources
     * @see UserAuthResource
     */
    @GetMapping
    public ResponseEntity<List<UserAuthResource>> getAllUsers() {
        var getAllUsersAuthQuery = new GetAllUsersAuthQuery();
        var usersAuth = userAuthQueryService.handle(getAllUsersAuthQuery);
        var userAuthResources = usersAuth.stream().map(UserAuthResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userAuthResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserAuthResource
     */
    @GetMapping(value = "/{userAuthId}")
    public ResponseEntity<UserAuthResource> getUserById(@PathVariable Long userId) {
        var getUserAuthByIdQuery = new GetUserAuthByIdQuery(userId);
        var userAuth = userAuthQueryService.handle(getUserAuthByIdQuery);
        if (userAuth.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserAuthResourceFromEntityAssembler.toResourceFromEntity(userAuth.get());
        return ResponseEntity.ok(userResource);
    }
}