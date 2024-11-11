package com.skillswap.platform.tutormatch.Users.Domain.Model.Aggregates;

import com.skillswap.platform.tutormatch.Users.Domain.Model.Command.CreateUserCommand;
import com.skillswap.platform.tutormatch.Users.Domain.Model.ValueObjects.*;
import com.skillswap.platform.tutormatch.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.PostPersist;

/**
 * Represents a user within the system, containing personal information and role details.
 * This entity is mapped to a database table and includes embedded value objects
 * such as {@code PersonName}, {@code EmailAddress}, {@code Password}, {@code Avatar},
 * {@code Gender}, {@code Semester}, and {@code Role}.
 * Inherits audit properties and behavior from {@code AuditableAbstractAggregateRoot}.
 */
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    private Password password;

    @Embedded
    private Avatar avatar;

    @Embedded
    private Gender gender;

    @Embedded
    private Semester semester;

    @Embedded
    private Role role;


    @Column(insertable=false, updatable=false)
    private Long tutorId;


    public User(String firstName, String lastName, String email, String avatarUrl,
                String gender, int semester, RoleType roleType, String password) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.avatar = new Avatar(avatarUrl);
        this.gender = new Gender(gender);
        this.semester = new Semester(semester);
        if (roleType == RoleType.teacher) {
            this.tutorId = null;
        }
        this.password = new Password(password);
    }

    /**
     * Constructs a new user instance based on the provided {@code CreateUserCommand}.
     *
     * @param command a {@code CreateUserCommand} containing user data
     */

    public User(CreateUserCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.password = new Password(command.password());
        this.avatar = new Avatar(command.avatarUrl());
        this.gender = new Gender(command.gender());
        this.semester = new Semester(command.semester());
        this.role = new Role(command.roleType());

    }

    public User() {}

    @PostPersist
    private void generateTutorId() {
        if (this.role.roleType() == RoleType.teacher && this.tutorId == null) {
            this.tutorId = getId();
        }
    }


    public void updateAvatar(String avatarUrl) {
        this.avatar = new Avatar(avatarUrl);
    }

    public void updateGender(String gender) {
        this.gender = new Gender(gender);
    }

    public void updateSemester(int semester) {
        this.semester = new Semester(semester);
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmailAddress() {
        return email.address();
    }

    public String getAvatarUrl() {
        return avatar.avatarUrl();
    }

    public String getGender() {
        return gender.gender();
    }

    public int getSemester() {
        return semester.semester();
    }

    public RoleType getRoleType() {
        return role.roleType();
    }

    public Long getTutorId() {
        return role.tutorId();
    }
}
