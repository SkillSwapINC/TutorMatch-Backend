package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllTutoringsQuery;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.TutoringSessionCommandService;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.TutoringSessionQueryService;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.CreateTutoringSessionResource;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.TutoringSessionResource;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform.CreateTutoringSessionCommandFromResourceAssembler;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform.TutoringSessionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing tutoring sessions.
 */
@RestController
@RequestMapping(value = "/api/v1/tutorings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tutorings", description = "Tutoring Management Endpoints")
public class TutoringSessionController {

    private final TutoringSessionCommandService tutoringSessionCommandService;
    private final TutoringSessionQueryService tutoringSessionQueryService;

    public TutoringSessionController(TutoringSessionCommandService tutoringSessionCommandService, TutoringSessionQueryService tutoringSessionQueryService) {
        this.tutoringSessionCommandService = tutoringSessionCommandService;
        this.tutoringSessionQueryService = tutoringSessionQueryService;
    }

    /**
     * Creates a new tutoring session with the provided data.
     *
     * @param resource the tutoring session data to be created
     * @return a response entity containing the created tutoring session resource,
     * or a bad request response if the creation fails
     */
    @Operation(
            summary = "Create a Tutoring",
            description = "Creates a Tutoring with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutoring created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<TutoringSessionResource> createTutoring(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Tutoring data",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreateTutoringSessionResource.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "courseName": "string",
                                      "description": "string",
                                      "price": 0,
                                      "times": [
                                        {"dayOfWeek": 0, "availableHours": []},
                                        {"dayOfWeek": 1, "availableHours": []},
                                        {"dayOfWeek": 2, "availableHours": []},
                                        {"dayOfWeek": 3, "availableHours": []},
                                        {"dayOfWeek": 4, "availableHours": []},
                                        {"dayOfWeek": 5, "availableHours": []},
                                        {"dayOfWeek": 6, "availableHours": []}
                                      ],
                                      "image": "string",
                                      "tutorId": 0,
                                      "courseId": 0
                                    }""")
                    )
            )
            @Valid @RequestBody CreateTutoringSessionResource resource) {
        var createTutoringCommand = CreateTutoringSessionCommandFromResourceAssembler.toCommandFromResource(resource);
        var tutoring = tutoringSessionCommandService.handle(createTutoringCommand);

        if (tutoring.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var tutoringResource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(tutoring.get());
        return new ResponseEntity<>(tutoringResource, HttpStatus.CREATED);
    }

    /**
     * REST endpoint to retrieve all available tutoring sessions.
     *
     * @return A list of {@link TutoringSessionResource} objects representing all tutoring sessions.
     */
    @Operation(
            summary = "Get all Tutoring",
            description = "Get all Tutoring with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutoring Found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<TutoringSessionResource>> getAllTutorings() {
        var getAllTutoringsQuery = new GetAllTutoringsQuery();
        var tutoring = tutoringSessionQueryService.handle(getAllTutoringsQuery);
        var tutoringResources = tutoring.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tutoringResources, HttpStatus.OK);
    }
}
