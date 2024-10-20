package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.spring.entities.Course;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Long> {


    List<Course> findByDuration(int duration);


    Logger logger = LoggerFactory.getLogger(ICourseRepository.class);

    default List<Course> findCoursesByDuration(int duration) {
        logger.debug("Finding courses with duration: {}", duration);
        return findByDuration(duration);
    }
}
