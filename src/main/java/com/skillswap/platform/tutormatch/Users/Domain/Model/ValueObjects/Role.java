package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class Role {
    private RoleType roleType;

    public Role() {}

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

}