package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class JunitTest {

    @Mock
    private IInstructorServices iInstructorServices;

    @InjectMocks
    private Instructor instructor1 = new Instructor(1L, "John", "Doe", LocalDate.of(2020, 1, 1), new HashSet<>());

    @Test
    @Order(0)
    void testAddinstructors() {
        // Mocking the behavior of addInstructor
        when(iInstructorServices.addInstructor(Mockito.any(Instructor.class))).thenReturn(instructor1);

        Instructor addedInstructor = iInstructorServices.addInstructor(instructor1);
        assertNotNull(addedInstructor);
        assertEquals("John", addedInstructor.getFirstName());
        log.info("Added instructor: {}", addedInstructor);
    }

    @Test
    @Order(1)
    void testUpdateinstructors() {
        // Mocking the behavior of updateInstructor
        instructor1.setFirstName("updatedInstructor");
        when(iInstructorServices.updateInstructor(Mockito.any(Instructor.class))).thenReturn(instructor1);

        Instructor updatedInstructor = iInstructorServices.updateInstructor(instructor1);
        assertEquals("updatedInstructor", updatedInstructor.getFirstName());
        log.info("Updated Instructor: {}", updatedInstructor);
    }

    @Test
    @Order(2)
    void testRetrieveAllinstructors() {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(instructor1);
        when(iInstructorServices.retrieveAllInstructors()).thenReturn(instructors);

        List<Instructor> retrievedInstructors = iInstructorServices.retrieveAllInstructors();
        assertTrue(retrievedInstructors.size() > 0);
        log.info("Instructors count: {}", retrievedInstructors.size());
    }
}
