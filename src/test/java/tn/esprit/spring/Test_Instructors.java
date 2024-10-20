package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class Test_Instructors {

    @Mock
    IInstructorRepository instructorRepository;

    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    InstructorServicesImpl instructorService;

    // Creating a sample Instructor object for testing
    Instructor instructor1 = new Instructor(1L, "John", "Doe", LocalDate.of(2020, 1, 1), new HashSet<>());

    // Creating a list of Instructors for testing
    List<Instructor> listInstructors = new ArrayList<Instructor>() {
        {
            add(new Instructor(2L, "Alice", "Smith", LocalDate.of(2019, 2, 2), new HashSet<>()));
            add(new Instructor(3L, "Bob", "Johnson", LocalDate.of(2018, 3, 3), new HashSet<>()));
        }
    };

    @Test
    public void testRetrieveAllInstructors() {
        // Mocking the behavior of the repository
        Mockito.when(instructorRepository.findAll()).thenReturn(listInstructors);

        // Retrieving all Instructors
        List<Instructor> instructors = instructorService.retrieveAllInstructors();

        // Asserting that the retrieved list is not null and has the expected size
        Assertions.assertNotNull(instructors);
        Assertions.assertEquals(2, instructors.size());
    }

    @Test
    public void testRetrieveInstructor() {
        // Mocking the behavior of the repository
        Mockito.when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor1));

        // Retrieving the Instructor
        Instructor retrievedInstructor = instructorService.retrieveInstructor(1L);

        // Asserting that the retrieved Instructor is not null
        Assertions.assertNotNull(retrievedInstructor);
        Assertions.assertEquals("John", retrievedInstructor.getFirstName());
    }

    @Test
    public void testAddInstructor() {
        // Mocking the behavior of the repository
        Mockito.when(instructorRepository.save(instructor1)).thenReturn(instructor1);

        // Adding the Instructor
        Instructor addedInstructor = instructorService.addInstructor(instructor1);

        // Asserting that the added Instructor is not null and matches the expected object
        Assertions.assertNotNull(addedInstructor);
        Assertions.assertEquals("John", addedInstructor.getFirstName());
    }




    @Test
    void testAddInstructorAndAssignToCourse() {
        // Mocking the behavior of the course repository
        // Adjusting the course creation to match the expected constructor
        Course course = new Course(1L, 3, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100.0f, 10, new HashSet<>());

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Mocking the behavior of the instructor repository
        Mockito.when(instructorRepository.save(instructor1)).thenReturn(instructor1);

        // Adding the Instructor and assigning it to a course
        Instructor assignedInstructor = instructorService.addInstructorAndAssignToCourse(instructor1, 1L);

        // Asserting that the assigned Instructor has the course assigned correctly
        Assertions.assertNotNull(assignedInstructor);
        Assertions.assertEquals(1, assignedInstructor.getCourses().size());
        Assertions.assertTrue(assignedInstructor.getCourses().contains(course));
    }

}