package com.skillswap.platform.tutormatch.IAM.Application.internal.commandservices;


import com.skillswap.platform.tutormatch.IAM.Domain.Model.Commands.SeedRolesCommand;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.Entities.Role;
import com.skillswap.platform.tutormatch.IAM.Domain.Model.ValueObjects.Roles;
import com.skillswap.platform.tutormatch.IAM.Domain.Services.RoleCommandService;
import com.skillswap.platform.tutormatch.IAM.Infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of {@link RoleCommandService} to handle {@link SeedRolesCommand}
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * This method will handle the {@link SeedRolesCommand} and will create the roles if not exists
     * @param command {@link SeedRolesCommand}
     * @see SeedRolesCommand
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        } );
    }
}