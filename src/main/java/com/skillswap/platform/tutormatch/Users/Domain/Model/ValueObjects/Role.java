package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import java.util.concurrent.atomic.AtomicInteger;

public record Role(RoleType roleType, Integer tutorId) {
    private static final AtomicInteger tutorIdCounter = new AtomicInteger(0);

    public Role(RoleType roleType) {
        this(roleType, roleType == RoleType.teacher ? generateTutorId() : null);
    }

    private static int generateTutorId() {
        return tutorIdCounter.incrementAndGet();
    }

    public RoleType roleType() {
        return roleType;
    }

    public Integer tutorId() {
        return tutorId;
    }
}
