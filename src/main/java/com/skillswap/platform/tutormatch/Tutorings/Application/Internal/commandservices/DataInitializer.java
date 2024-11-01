package com.skillswap.platform.tutormatch.Tutorings.Application.Internal.commandservices;

import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Course;
import com.skillswap.platform.tutormatch.Tutorings.Domain.Model.Entities.Semester;
import com.skillswap.platform.tutormatch.Tutorings.Infrastructure.persistence.jpa.repositories.SemesterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for loading initial data into the database,
 * including semesters and associated courses.
 */
@Service
public class DataInitializer {

    private final SemesterRepository semesterRepository;
    private final SemesterService semesterService;

    /**
     * Constructs a new DataInitializer with the specified SemesterRepository and SemesterService.
     *
     * @param semesterRepository the repository used to manage Semester entities
     * @param semesterService the service responsible for additional semester operations
     */
    public DataInitializer(SemesterRepository semesterRepository, SemesterService semesterService) {
        this.semesterRepository = semesterRepository;
        this.semesterService = semesterService;
    }

    /**
     * Loads the initial data into the database, ensuring that all semesters and
     * their associated courses are saved.
     * This method is wrapped in a transaction to ensure atomicity.
     */
    @Transactional
    public void loadInitialData() {
        List<Semester> semesters = createSemesters();
        semesterRepository.saveAll(semesters);

        for (Semester semester : semesters) {
            semesterService.assignCoursesToSemester(semester);
        }
    }

    /**
     * Creates a list of semesters along with their respective courses.
     *
     * @return a list of predefined Semester entities,
     * each containing a list of associated Course entities
     */
    private List<Semester> createSemesters() {
        List<Semester> semesters = new ArrayList<>();

        semesters.add(new Semester("First", List.of(
                new Course("Introducción a los Algoritmos", "Fundamentos de algoritmos y su aplicación.", 1, null)
        )));

        semesters.add(new Semester("Second", List.of(
                new Course("Algoritmos", "Estudio de algoritmos avanzados y su análisis.", 2, null)
        )));

        semesters.add(new Semester("Third", List.of(
                new Course("Algoritmos y Estructuras de Datos", "Estructuras de datos y su uso en algoritmos.", 3, null),
                new Course("Diseño y Patrones de Software", "Principios de diseño y patrones de software.", 3, null)
        )));

        semesters.add(new Semester("Fourth", List.of(
                new Course("IHC y Tecnologías Móviles", "Interacción Humano-Computadora y desarrollo móvil.", 4, null),
                new Course("Diseño de Base de Datos", "Modelado y diseño de bases de datos.", 4, null)
        )));

        semesters.add(new Semester("Fifth", List.of(
                new Course("Desarrollo de Aplicaciones Open Source", "Principios y prácticas del desarrollo Open Source.", 5, null),
                new Course("Aplicaciones Web", "Desarrollo de aplicaciones para la web.", 5, null)
        )));

        semesters.add(new Semester("Sixth", List.of(
                new Course("Aplicaciones para Dispositivos Móviles", "Desarrollo de aplicaciones para móviles.", 6, null),
                new Course("Complejidad Algorítmica", "Estudio de la complejidad de algoritmos.", 6, null)
        )));

        semesters.add(new Semester("Seventh", List.of(
                new Course("Diseño de Experimentos de Ingeniería de Software", "Diseño de experimentos en el contexto del software.", 7, null),
                new Course("Fundamentos de Arquitectura de Software", "Principios de arquitectura de software.", 7, null)
        )));

        semesters.add(new Semester("Eighth", List.of(
                new Course("Arquitecturas de Software Emergentes", "Nuevas tendencias en arquitecturas de software.", 8, null),
                new Course("Gerencia de Proyectos en Computación", "Gestión de proyectos en el ámbito de la computación.", 8, null)
        )));

        return semesters;
    }
}

