package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.queryservices;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Aggregate.TutoringSession;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllTutoringsByTutorId;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllTutoringsQuery;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetTutoringById;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetTutoringBySemesterId;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.TutoringSessionQueryService;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.TutoringSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for querying tutoring sessions.
 * <p>
 * Provides methods to retrieve tutoring sessions based on various criteria,
 * such as all tutoring sessions or those belonging to a specific semester.
 */
@Service
public class TutoringSessionQueryServiceImpl implements TutoringSessionQueryService {

    private final TutoringSessionRepository tutoringSessionRepository;

    public TutoringSessionQueryServiceImpl(TutoringSessionRepository tutoringSessionRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;

    }

    /**
     * Retrieves all available tutoring sessions.
     *
     * @param query The query object (unused in this implementation).
     * @return A list of all tutoring sessions.
     */
    @Override
    public List<TutoringSession> handle(GetAllTutoringsQuery query){
        return tutoringSessionRepository.findAll();
    }

    /**
     * Retrieves tutoring sessions associated with a specific semester.
     *
     * @param query The query object containing the semester ID.
     * @return A list of tutoring sessions belonging to the specified semester.
     */
    @Override
    public List<TutoringSession> handle(GetTutoringBySemesterId query){
        return tutoringSessionRepository.findByCycle(query.cycle());
    }


    @Override
    public List<TutoringSession> handle(GetAllTutoringsByTutorId query) {
        return tutoringSessionRepository.findTutoringByTutorId(query.tutorId());
    }


    @Override
    public Optional<TutoringSession> handle(GetTutoringById query){
        return tutoringSessionRepository.findById(query.id());
    }

}
