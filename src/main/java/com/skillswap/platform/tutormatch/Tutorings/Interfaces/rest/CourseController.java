package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetTutoringBySemesterId;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.TutoringSessionQueryService;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.TutoringSessionResource;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform.TutoringSessionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing tutoring sessions associated with courses.
 * Handles requests for retrieving tutoring sessions by semester.
 */
@RestController
@RequestMapping(value = "/api/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Courses Management Endpoints")
public class CourseController {

    private final TutoringSessionQueryService tutoringSessionQueryService;

    public CourseController(TutoringSessionQueryService tutoringSessionQueryService) {
        this.tutoringSessionQueryService = tutoringSessionQueryService;
    }

    /**
     * Retrieves all tutoring sessions associated with a specific semester (cycle).
     *
     * @param cycle The semester (cycle) number to filter by.
     * @return A list of {@link TutoringSessionResource} objects representing the retrieved tutoring sessions.
     * @throws IllegalArgumentException If the provided cycle is invalid.
     */
    @GetMapping("/{cycle}")
    public ResponseEntity<List<TutoringSessionResource>> getTutoringsByCycle(@PathVariable int cycle) {
        var getTutoringsByCycleQuery = new GetTutoringBySemesterId(cycle);
        var tutoring = tutoringSessionQueryService.handle(getTutoringsByCycleQuery);
        var tutoringResources = tutoring.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tutoringResources, HttpStatus.OK);
    }
}
