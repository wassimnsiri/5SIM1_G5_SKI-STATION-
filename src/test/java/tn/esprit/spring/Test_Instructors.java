package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class Test_Instructors {

    @Mock
    IInstructorRepository instructorRepository;

    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    InstructorServicesImpl instructorService;

    Instructor instructor1 = new Instructor(1L, "John", "Doe", LocalDate.of(2020, 1, 1), new HashSet<>());

    List<Instructor> listInstructors = new ArrayList<Instructor>() {
        {
            add(new Instructor(2L, "Alice", "Smith", LocalDate.of(2019, 2, 2), new HashSet<>()));
            add(new Instructor(3L, "Bob", "Johnson", LocalDate.of(2018, 3, 3), new HashSet<>()));
        }
    };

    @Test
    public void testRetrieveAllInstructors() {
        Mockito.when(instructorRepository.findAll()).thenReturn(listInstructors);
        List<Instructor> instructors = instructorService.retrieveAllInstructors();
        Assertions.assertNotNull(instructors);
        Assertions.assertEquals(2, instructors.size());
    }

    @Test
    public void testRetrieveInstructor() {
        Mockito.when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor1));
        Instructor retrievedInstructor = instructorService.retrieveInstructor(1L);
        Assertions.assertNotNull(retrievedInstructor);
        Assertions.assertEquals("John", retrievedInstructor.getFirstName());
    }

    @Test
    public void testAddInstructor() {
        Mockito.when(instructorRepository.save(instructor1)).thenReturn(instructor1);
        Instructor addedInstructor = instructorService.addInstructor(instructor1);
        Assertions.assertNotNull(addedInstructor);
        Assertions.assertEquals("John", addedInstructor.getFirstName());
    }

    @ParameterizedTest
    @MethodSource("instructorData")
    public void testAddInstructor_WithVariousNames(String firstName, String lastName) {
        Instructor newInstructor = new Instructor(4L, firstName, lastName, LocalDate.now(), new HashSet<>());
        Mockito.when(instructorRepository.save(newInstructor)).thenReturn(newInstructor);
        Instructor addedInstructor = instructorService.addInstructor(newInstructor);
        Assertions.assertNotNull(addedInstructor);
        Assertions.assertEquals(firstName, addedInstructor.getFirstName());
        Assertions.assertEquals(lastName, addedInstructor.getLastName());
    }

    private static Stream<Arguments> instructorData() {
        return Stream.of(
                Arguments.of("Alice", "Wonderland"),
                Arguments.of("Bob", "Builder"),
                Arguments.of("Charlie", "Brown")
        );
    }

    @Test
    public void testRetrieveInstructor_NotFound() {
        Mockito.when(instructorRepository.findById(999L)).thenReturn(Optional.empty());
        Instructor retrievedInstructor = instructorService.retrieveInstructor(999L);
        Assertions.assertNull(retrievedInstructor);
    }

    

    @Test
    void testAddInstructorAndAssignToCourse() {
        Course course = new Course(1L, 3, TypeCourse.COLLECTIVE_ADULT, Support.SKI, 100.0f, 10, new HashSet<>());
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Mockito.when(instructorRepository.save(instructor1)).thenReturn(instructor1);

        Instructor assignedInstructor = instructorService.addInstructorAndAssignToCourse(instructor1, 1L);

        Assertions.assertNotNull(assignedInstructor);
        Assertions.assertEquals(1, assignedInstructor.getCourses().size());
        Assertions.assertTrue(assignedInstructor.getCourses().contains(course));
    }
}
