package com.skillswap.platform.tutormatch.Tutorings.Interfaces.rest.resources;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.DailySchedule;

import java.util.List;

public record UpdateTutoringSessionResource(String description, Double price, List<DailySchedule> times, String image, String whatTheyWillLearn) {

}
