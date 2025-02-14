package ttl.larku.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ttl.larku.domain.Course;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.jconfig.LarkUTestDataConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = LarkUConfig.class)
//@SpringBootTest
@SpringBootTest(classes = {LarkUConfig.class, LarkUTestDataConfig.class})
//@SpringBootTest(classes = {CourseService.class, InMemoryCourseDAO.class})
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    public void setup() {
        System.out.println("CourseServiceTest");
        for(String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(context.getBeanDefinitionCount() + " beans");

        courseService.clear();
    }

    @Test
    public void testCreateCourse() {
        Course course = courseService.createCourse("Math-101", "Intro to Math");

        Course result = courseService.getCourse(course.getId());

        assertTrue(result.getCode().contains("Math-101"));
        assertEquals(1, courseService.getAllCourses().size());
    }

    @Test
    public void testDeleteCourse() {
        Course course1 = courseService.createCourse("Math-101", "Intro to Math");
        Course course2 = courseService.createCourse("Phys-101", "Intro to Physics");

        assertEquals(2, courseService.getAllCourses().size());

        courseService.deleteCourse(course1.getId());

        assertEquals(1, courseService.getAllCourses().size());
        assertTrue(courseService.getAllCourses().get(0).getCode().contains("Phys-101"));
    }

    @Test
    public void testDeleteNonExistentCourse() {
        Course course1 = courseService.createCourse("Math-101", "Intro to Math");
        Course course2 = courseService.createCourse("Phys-101", "Intro to Physics");

        assertEquals(2, courseService.getAllCourses().size());

        //Non existent Id
        courseService.deleteCourse(9999);

        assertEquals(2, courseService.getAllCourses().size());
    }

    @Test
    public void testUpdateCourse() {
        Course course1 = courseService.createCourse("Math-101", "Intro to Math");

        assertEquals(1, courseService.getAllCourses().size());

        course1.setCode("Math-202");
        courseService.updateCourse(course1);

        List<Course> courses = courseService.getAllCourses();

        assertEquals(1, courses.size());
        assertTrue(courses.get(0).getCode().contains("Math-202"));
    }
}
