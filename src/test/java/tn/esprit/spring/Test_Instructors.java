package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Test_Instructors {

    @Mock
    private IInstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // calculateInstructorSeniority
    @Test
    public void testCalculateInstructorSeniority() {
        Long instructorId = 1L;
        Instructor instructor = new Instructor();
        instructor.setDateOfHire(LocalDate.of(2010, 1, 1));

        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));

        int seniority = instructorServices.calculateInstructorSeniority(instructorId);

        assertEquals(14, seniority);
        verify(instructorRepository, times(1)).findById(instructorId);
    }

    // Test méthode getCoursesTaughtByInstructor
    @Test
    public void testGetCoursesTaughtByInstructor() {
        Long instructorId = 1L;
        Instructor instructor = new Instructor();
        Set<Course> courses = new HashSet<>();
        courses.add(new Course());
        instructor.setCourses(courses);

        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));

        Set<Course> resultCourses = instructorServices.getCoursesTaughtByInstructor(instructorId);

        assertNotNull(resultCourses);
        assertEquals(1, resultCourses.size());
        verify(instructorRepository, times(1)).findById(instructorId);
    }
}