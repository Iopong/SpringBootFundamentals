package ttl.larku.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.MyFactory;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

public class SpringApp {

    int value;

    static Logger logger = LoggerFactory.getLogger(SpringApp.class);

    public static void main(String[] args) {
        //primeAndPrintBoth();
        SpringApp sapp = new SpringApp();
//        sapp.postRequestToAddAStudent();
//        sapp.getRequestForAllStudents();
        sapp.getRequestForAllCourses();
//        sapp.testDAO();
    }


    public void postRequestToAddAStudent() {
        logger.info("Boo");
        //StudentService ss = new StudentService();
        StudentService ss = MyFactory.studentService();
        ss.createStudent("New One", "282 484 9944", Student.Status.FULL_TIME);

        List<Student> students = ss.getAllStudents();
        students.forEach(System.out::println);
    }

    public void testDAO() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseDAO<Student> dao = context.getBean("inMemoryStudentDAO", BaseDAO.class);

        List<Student> ll = dao.getAll();

    }
    public void getRequestForAllStudents() {
        //StudentService ss = new StudentService();
//        StudentService ss = MyFactory.studentService();
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class, OtherConfig.class);
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        StudentService ss = context.getBean("studentService", StudentService.class);
//        StudentService ss = (StudentService) context.getBean("studentService");

//        StudentService ss2 = context.getBean("studentService2", StudentService.class);

        List<Student> students = ss.getAllStudents();
        System.out.println("All Students: " + students.size());
        students.forEach(System.out::println);
    }

    public void getRequestForAllCourses() {
        //StudentService ss = new StudentService();
//        StudentService ss = MyFactory.studentService();
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class, OtherConfig.class);
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        CourseService ss = context.getBean("courseService", CourseService.class);
        init(ss);
//        StudentService ss = (StudentService) context.getBean("studentService");

//        StudentService ss2 = context.getBean("studentService2", StudentService.class);

        List<Course> courses = ss.getAllCourses();
        System.out.println("All Coruses: " + courses.size());
        courses.forEach(System.out::println);
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
