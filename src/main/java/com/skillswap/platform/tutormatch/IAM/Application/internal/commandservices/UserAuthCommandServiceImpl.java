package com.skillswap.platform.tutormatch.IAM.Application.internal.commandservices;


import com.skillswap.platform.tutormatch.IAM.Application.internal.outboundservices.hashing.HashingService;
import com.skillswap.platform.tutormatch.IAM.Application.internal.outboundservices.tokens.TokenService;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Aggregates.UserAuth;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SignInCommand;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SignUpCommand;
import com.skillswap.platform.tutormatch.IAM.Domain.Services.UserAuthCommandService;
import com.skillswap.platform.tutormatch.IAM.Infrastructure.persistence.jpa.repositories.RoleRepository;
import com.skillswap.platform.tutormatch.IAM.Infrastructure.persistence.jpa.repositories.UserAuthRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserAuthCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserAuthCommandServiceImpl implements UserAuthCommandService {

    private final UserAuthRepository userAuthRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    public UserAuthCommandServiceImpl(UserAuthRepository userAuthRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userAuthRepository = userAuthRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<UserAuth, String>> handle(SignInCommand command) {
        var userAuth = userAuthRepository.findByUsername(command.username());
        if (userAuth.isEmpty())
            throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), userAuth.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(userAuth.get().getUsername());
        return Optional.of(ImmutablePair.of(userAuth.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<UserAuth> handle(SignUpCommand command) {
        if (userAuthRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var userAuth = new UserAuth(command.username(), hashingService.encode(command.password()), roles);
        userAuthRepository.save(userAuth);
        return userAuthRepository.findByUsername(command.username());
    }
}