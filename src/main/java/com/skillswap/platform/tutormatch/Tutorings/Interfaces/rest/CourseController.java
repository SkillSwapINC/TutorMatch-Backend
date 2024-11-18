package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllCoursesQuery;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetCourseByCycle;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.CourseQueryService;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources.CourseResource;
import com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.transform.CourseResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing tutoring sessions associated with courses.
 * Handles requests for retrieving tutoring sessions by semester.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Courses Management Endpoints")
public class CourseController {

    private final CourseQueryService courseQueryService;

    public CourseController(CourseQueryService courseQueryService) {
        this.courseQueryService = courseQueryService;
    }

    /**
     * Retrieves all available courses.
     *
     * @return A list of {@link CourseResource} objects representing the retrieved courses.
     */

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResource>> getAllCourses(@RequestParam(required = false) Integer cycle) {
        List<CourseResource> courseResources;

        if (cycle != null) {
            var getCoursesByCycleQuery = new GetCourseByCycle(cycle);
            var courses = courseQueryService.handle(getCoursesByCycleQuery);
            courseResources = courses.stream()
                    .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
        } else {
            var getAllCoursesQuery = new GetAllCoursesQuery();
            var courses = courseQueryService.handle(getAllCoursesQuery);
            courseResources = courses.stream()
                    .map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(courseResources, HttpStatus.OK);
    }

}
