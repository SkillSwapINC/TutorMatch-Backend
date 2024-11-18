package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.queryservices;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetAllCoursesQuery;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Queries.GetCourseByCycle;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Services.CourseQueryService;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for querying courses.
 * <p>
 * Provides methods to retrieve courses based on various criteria,
 * such as all courses.
 */
@Service
public class CourseQueryServiceImpl implements CourseQueryService {

    private final CourseRepository courseRepository;

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;

    }

    /**
     * Retrieves all available courses
     * @param query The query object (unused in this implementation).
     * @return A list of all courses.
     *
     **/
    @Override
    public List<Course> handle(GetAllCoursesQuery query){
        return courseRepository.findAll();
    }

    /**
     * Retrieves a course by its ID.
     * @param query The query object containing the course ID.
     * @return The course with the specified ID, if found.
     */
    @Override
    public List<Course> handle(GetCourseByCycle query){
        return courseRepository.findByCycle(query.cycle());
    }


}
