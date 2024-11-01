package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.commandservices;

import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.SemesterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Component responsible for loading data into the database upon application startup
 * if no semesters are found in the repository.
 */
@Component
public class DataLoader {

    private final SemesterRepository semesterRepository;
    private final DataInitializer dataInitializer;

    /**
     * Constructs a new DataLoader with the specified SemesterRepository and DataInitializer.
     *
     * @param semesterRepository the repository used to check for existing semesters
     * @param dataInitializer the service used to populate the initial data into the database
     */
    public DataLoader(SemesterRepository semesterRepository, DataInitializer dataInitializer) {
        this.semesterRepository = semesterRepository;
        this.dataInitializer = dataInitializer;
    }

    /**
     * Defines a CommandLineRunner bean that runs at application startup. If no semesters exist
     * in the repository, it triggers the loading of initial data.
     *
     * @return a CommandLineRunner that initializes data upon application startup
     */
    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            if (semesterRepository.count() == 0) {
                dataInitializer.loadInitialData();
            }
        };
    }
}

