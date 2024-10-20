package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tn.esprit.spring.entities.Support.SKI;
import static tn.esprit.spring.entities.TypeCourse.COLLECTIVE_ADULT;
import static tn.esprit.spring.entities.TypeCourse.INDIVIDUAL;

@ExtendWith(MockitoExtension.class)
public class CourseServiceMockTest {
    @Mock
    private ICourseRepository courseRepository;  // Le mock du repository
    @InjectMocks
    private CourseServicesImpl courseServices;   // La classe de service à tester
    @Test
    public void testFindCoursesByDuration() {

        Registration registration = new Registration();
        Set<Registration> registrations = new HashSet<>();
        registrations.add(registration);

        List<Course> courses = Arrays.asList(
                new Course(2L, 1, INDIVIDUAL, SKI, 20.5f, 2, "skidiving", "Don't worry be happy", 60, registrations),
                new Course(3L, 1, COLLECTIVE_ADULT, SKI, 20.5f, 2, "drive", "Don't worry be happy", 60, registrations)
        );
        // Simuler le comportement du repository
        when(courseRepository.findByDuration(60)).thenReturn(courses);
        // Appel du service pour récupérer les cours avec la durée 60
        List<Course> foundCourses = courseServices.findCoursesByDuration(60);
        // Vérifiez que deux cours ont été trouvés
        assertEquals(2, foundCourses.size());
        // Vérifiez que le repository a bien été appelé avec la bonne durée
        verify(courseRepository);
        courseRepository.findByDuration(60);
    }
}
