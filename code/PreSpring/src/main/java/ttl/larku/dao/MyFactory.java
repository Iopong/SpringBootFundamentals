package ttl.larku.dao;

import java.util.ResourceBundle;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.dao.jpa.JpaCourseDAO;
import ttl.larku.dao.jpa.JpaStudentDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;


/**
 * GREAT - this factory will manage the creation of
 * our service object, and the associated DAO depending
 * on the environment which is set in the configuration.
 *
 * This is an example of a dependency injection. We started
 * out with our controller dependent on a particular service
 * with a hardcoded DAO.
 *
 * We realized that our DAO could change for MYSQL, Postgres, InMemory
 * which means we would have to change our services code. So we moved
 * our DAO implementation behind a generic interface. So that we could have
 * different DAO implementations of courses, and student.
 *
 * Then we created a factory to create our services, and return a DAO depending
 * on the environment that we are in. This factory pattern is dependent on us
 * having a configuration file that determines what our DAO is.
 *
 * Another approach is to pass the DAO through the constructor of the service. This
 * would happen through constructor dependency injection. Spring is essentially a
 * large factory that manages our object creation for us.
 */
public class MyFactory {

    private static BaseDAO<Student> studentDAO;

    public static BaseDAO<Student> studentDAO() {
        //NOT Thread safe.  But this will normally be called first
        //during application start up, which should generally happen
        //in a single Thread.  But still, this should probably be either
        //a java Singleton, or we should return a new instance each time
        if (studentDAO == null) {
            ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
            String profile = bundle.getString("larku.profile.active");
            switch (profile) {
                case "dev":
                    return studentDAO = new InMemoryStudentDAO();
                case "prod":
                    return studentDAO = new JpaStudentDAO();
                default:
                    throw new RuntimeException("No profile set");
            }
        } else {
            return studentDAO;
        }
    }

    //The way we have this set up, courseDAO's will
    //have "Prototype" scope, i.e. new instance returned
    //with each call.
    public static BaseDAO<Course> courseDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
        String profile = bundle.getString("larku.profile.active");
        switch (profile) {
            case "dev":
                return new InMemoryCourseDAO();
            case "prod":
                return new JpaCourseDAO();
            default:
                throw new RuntimeException("No profile set");
        }
    }

    public static StudentService studentService() {
        StudentService ss = new StudentService();

        ss.setStudentDAO(studentDAO());

        return ss;
    }

    public static CourseService courseService() {
        CourseService ss = new CourseService();

        ss.setCourseDAO(courseDAO());

        return ss;
    }
}
