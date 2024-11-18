package com.skillswap.platform.tutormatch.Tutorings.Domain.Services;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.CreateTutoringSessionCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.DeleteTutoringCommand;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command.UpdateTutoringCommand;

import java.util.Optional;

/**
 * Service interface for handling commands related to tutoring sessions.
 * Provides methods for processing and managing tutoring session commands.
 */
public interface TutoringSessionCommandService {

    /**
     * Handles the creation of a new tutoring session based on the provided command.
     *
     * @param command The {@link CreateTutoringSessionCommand} containing
     * the details needed to create a tutoring session.
     * @return An {@link Optional} containing the created {@link TutoringSession}
     * if successful, or an empty {@link Optional} if not.
     */
    Optional<TutoringSession> handle(CreateTutoringSessionCommand command);

    /**
     * Handles the update of an existing tutoring session based on the provided command.
     *
     * @param command The {@link UpdateTutoringCommand} containing
     * the details needed to update a tutoring session.
     * @return An {@link Optional} containing the updated {@link TutoringSession}
     * if successful, or an empty {@link Optional} if not.
     */

    Optional<TutoringSession> handle(UpdateTutoringCommand command);

    /**
     * Handles the deletion of a tutoring session based on the provided command.
     * @param command The {@link DeleteTutoringCommand} containing
     * @see DeleteTutoringCommand
     */

    void handle(DeleteTutoringCommand command);
}
