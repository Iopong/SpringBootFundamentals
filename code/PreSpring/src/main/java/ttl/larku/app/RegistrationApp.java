package ttl.larku.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ttl.larku.dao.MyFactory;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

public class RegistrationApp {

    int value;

    static Logger logger = LoggerFactory.getLogger(RegistrationApp.class);

    public static void main(String[] args) {
        //primeAndPrintBoth();
        postRequestToAddAStudent();
        getRequestForAllStudents();
    }


    public static void postRequestToAddAStudent() {
        logger.info("Boo");
        //StudentService ss = new StudentService();
        StudentService ss = MyFactory.studentService();
        ss.createStudent("New One", "282 484 9944", Student.Status.FULL_TIME);

        List<Student> students = ss.getAllStudents();
        students.forEach(System.out::println);
    }

    public static void getRequestForAllStudents() {
        // -- What is wrong here?
        // Wrong!! You are creating a new instance with each request.
        // which means that there will be a different in-memory object map being
        // created for each request.
        //StudentService ss = new StudentService();

        // -- What are alternative?
        // OPTION #1 - WRONG
        // we can make the DAO a singleTon by making the students hashMap
        // a static. Meaning every instance of the studentDAO will share the
        // the same instance.
        // but you don't want to make it a singleton to solve this problem. It has
        // to be a semantic decision among many more.

        // OPTION #2 - WRONG
        // We could create a private property for studentService that we use to
        // handle each request but having one object handle 1000s of request is not
        // feasible. NOT thread safe either.

        // OPTION #3
        // -- OKAY Factory will return a new instance of a student service
        // and in that service we will return a single instance of the DAO if
        // if has been instantiated or create it has not been created. This
        // however would not be thread safe for the DAO.
        StudentService ss = MyFactory.studentService();
        List<Student> students = ss.getAllStudents();
        System.out.println("All Students: " + students.size());
        students.forEach(System.out::println);
    }

    public static void primeAndPrintBoth() {
        //StudentService ss = new StudentService();
        StudentService ss = MyFactory.studentService();
        init(ss);
        List<Student> students = ss.getAllStudents();
        students.forEach(System.out::println);

        CourseService cs = MyFactory.courseService();
        init(cs);
        List<Course> courses = cs.getAllCourses();
        courses.forEach(System.out::println);

    }

    public static void init(StudentService ss) {
        ss.createStudent("Manoj", "282 939 9944", Student.Status.FULL_TIME);
        ss.createStudent("Charlene", "282 898 2145", Student.Status.FULL_TIME);
        ss.createStudent("Firoze", "228 678 8765", Student.Status.HIBERNATING);
        ss.createStudent("Joe", "3838 678 3838", Student.Status.PART_TIME);
    }

    public static void init(CourseService cs) {
        cs.createCourse("Math-101", "Intro To Math");
        cs.createCourse("Math-201", "More Math");
        cs.createCourse("Phys-101", "Baby Physics");
    }

}
