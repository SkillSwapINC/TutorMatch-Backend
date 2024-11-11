package com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects;

import java.util.concurrent.atomic.AtomicLong;

public record Role(RoleType roleType, Long tutorId) {
    private static final AtomicLong tutorIdCounter = new AtomicLong(0);

    public Role(RoleType roleType) {
        this(roleType, roleType == RoleType.teacher ? generateTutorId() : null);
    }

    private static Long generateTutorId() {
        return tutorIdCounter.incrementAndGet();
    }

    public RoleType roleType() {
        return roleType;
    }

    public Long tutorId() {
        return tutorId;
    }
}
