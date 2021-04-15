package ttl.larku.service;

import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.domain.Student.Status;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    List<String> stuff = new ArrayList<>();

    // You are delegating the database type creation to
    // the service which isn't feasible. We would have to
    // change this everytime the database changes. VERY BAD
    //private InMemoryStudentDAO studentDAO;
    private BaseDAO<Student> studentDAO;

    // You are making a new InMemoryStudentDAO with each student service
    // again we are hardcoding our database into the service which isn't
    // good at all. VERY BAD.
    public StudentService() {
        studentDAO = new InMemoryStudentDAO();
    }

    public Student createStudent(String name, String phoneNumber, Status status) {
        Student student = new Student(name, phoneNumber, status);
        student = studentDAO.create(student);

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

    public BaseDAO<Student> getStudentDAO() {
        return studentDAO;
    }


    // -- GOOD we can delegate this to a factory that will set the DAO depending
    // on which environment we are in.
    public void setStudentDAO(BaseDAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }
}
