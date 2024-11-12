package com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Command;


import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.DailySchedule;

import java.util.List;

/**
 * Command for creating a new tutoring session.
 * Contains all necessary information for setting up a tutoring session,
 * including title, description,
 * price, available times, image, tutor ID, and course ID.
 *
 * @param courseName     The title of the tutoring session.
 * @param description The description of the tutoring session.
 * @param price     The price of the tutoring session.
 * @param times     A list of {@link DailySchedule} objects representing available
 * times for each day of the week.
 * Each {@link DailySchedule} specifies the day and available hours.
 * @param image     The URL or path to an image associated with the tutoring session.
 * @param tutorId   The ID of the tutor who offers this tutoring session.
 * @param course  The ID of the course associated with this tutoring session.
 */
public record CreateTutoringSessionCommand(
        String courseName,
        String description,
        Double price,
        List<DailySchedule> times,
        String image,
        Long tutorId,
        Long courseId,
        Course course
) {}
