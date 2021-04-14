package ttl.larku.service;

import ttl.larku.dao.TheFactory;
import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.domain.Student.Status;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    List<String> stuff = new ArrayList<>();

    //    private InMemoryStudentDAO studentDAO;
//    private JpaStudentDAO studentDAO;
    private StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
//        studentDAO = new InMemoryStudentDAO();
//        studentDAO = new JpaStudentDAO();
        //studentDAO = DAOFactory.getStudentDAO();
        this.studentDAO = studentDAO;
    }

//    public StudentService() {
////        studentDAO = new InMemoryStudentDAO();
////        studentDAO = new JpaStudentDAO();
//        studentDAO = TheFactory.getStudentDAO();
//    }

    public Student createStudent(String name, String phoneNumber, Status status) {
        Student student = new Student(name, phoneNumber, status);
        student = createStudent(student);
        return student;
    }

    public Student createStudent(Student student) {
        student = studentDAO.create(student);

        return student;
    }

    public void deleteStudent(int id) {
        Student student = studentDAO.get(id);
        if (student != null) {
            studentDAO.delete(student);
        }
    }

    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    public Student getStudent(int id) {
        return studentDAO.get(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

//    public JpaStudentDAO getStudentDAO() {
//        return studentDAO;
//    }

}
