package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import java.util.HashSet;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tn.esprit.spring.entities.Support.SKI;
import static tn.esprit.spring.entities.TypeCourse.INDIVIDUAL;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private ICourseServices courseServices;

    @Test
    public void testAddCourse() {
        // Créez une instance de Registration
        Registration registration = new Registration();
        // Créez un Set de Registration et ajoutez-y votre instance
        Set<Registration> registrations = new HashSet<>();
        registrations.add(registration);
        // Créez un objet Course avec le Set de Registration
        Course course = new Course(2L, 1, INDIVIDUAL, SKI, 20.5f, 2,"skidiving",
                "Don't worry be happy",20, registrations);
        // Ajoutez le cours en appelant la méthode du service
        Course addedCourse = courseServices.addCourse(course);
        // Assurez-vous que le cours ajouté n'est pas nul
        assertNotNull(addedCourse);
        // Vérifiez que le cours ajouté a le bon name de cours
        assertEquals("skidiving", addedCourse.getName());
    }
}

