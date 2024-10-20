package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
class JunitTest {
    @Autowired
    private IInstructorServices iInstructorServices;

    @Test
    @Order(0)
     void testAddinstructors() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("Test");
        Instructor addInstructor = iInstructorServices.addInstructor(instructor);
        assertNotNull(addInstructor);
        log.info("Added insturctor: {}", addInstructor);
    }

    /*@Test
    @Order(1)
     void testUpdateinstructors() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("test2");
        Instructor addInstructor = iInstructorServices.addInstructor(instructor);
        addInstructor.setFirstName("updatedInstructor");
        Instructor updatedInstructor = iInstructorServices.updateInstructor(addInstructor);
        assertEquals("updatedInstructor", updatedInstructor.getFirstName());
        log.info("Updated Instructor: {}", updatedInstructor);
    }

    @Test
    @Order(2)
     void testRetrieveAllinstructors() {
        List<Instructor> instructors = iInstructorServices.retrieveAllInstructors();
        assertTrue(instructors.size() > 0);
        log.info("instructors count: {}", instructors.size());
    }*/
}