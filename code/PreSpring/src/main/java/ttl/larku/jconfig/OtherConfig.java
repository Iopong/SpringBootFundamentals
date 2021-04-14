package ttl.larku.jconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

//@Configuration
//public class OtherConfig {
//
////    <bean id="inMemoryStudentDAO" class="ttl.larku.dao.inmemory.InMemoryStudentDAO" scope="prototype"/>
//
////    <bean id="inMemoryStudentDAO" class="ttl.larku.dao.inmemory.InMemoryStudentDAO"/>
//    @Bean
//    public BaseDAO<Course> inMemoryCourseDAO() {
//        return new InMemoryCourseDAO();
//    }
//
//    @Bean
//    public CourseService courseService() {
//        CourseService cs = new CourseService();
//        cs.setCourseDAO(inMemoryCourseDAO());
//        return cs;
//    }
//}