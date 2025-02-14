package ttl.larku.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Student;
import ttl.larku.domain.Student.Status;
import ttl.larku.domain.StudentCreatedEvent;
import ttl.larku.service.props.ServiceThatWeDontOwn;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

//    @Autowired
    private BaseDAO<Student> studentDAO;

    private ApplicationEventPublisher publisher;

//    @Autowired
    private ServiceThatWeDontOwn ctwdo;

    public StudentService(BaseDAO<Student> studentDAO, ApplicationEventPublisher publisher, ServiceThatWeDontOwn ctwdo) {
        this.studentDAO = studentDAO;
        this.publisher = publisher;
        this.ctwdo = ctwdo;
    }


    public Student createStudent(String name) {
        Student student = new Student(name);
        student = studentDAO.create(student);

        return student;
    }

    public Student createStudent(String name, String phoneNumber, Status status) {
        return createStudent(new Student(name, phoneNumber, status));
    }

    public Student createStudent(Student student) {
        student = studentDAO.create(student);

        System.out.println("Going to publish in " + Thread.currentThread());
        publisher.publishEvent(new StudentCreatedEvent(this, student));

        return student;
    }

    public boolean deleteStudent(int id) {
        Student student = studentDAO.get(id);
        if (student != null) {
            studentDAO.delete(student);
            return true;
        }
        return false;
    }

    public boolean updateStudent(Student student) {
        Student inDB = studentDAO.get(student.getId());
        if (inDB != null) {
            studentDAO.update(student);
            return true;
        }
        return false;
    }

    public Student getStudent(int id) {
        Duration timeout = ctwdo.getTimeout();
        System.out.println("Timout is: " + timeout);
        return studentDAO.get(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public List<Student> getByName(String name) {
        String lc = name.toLowerCase();
        List<Student> result = getAllStudents()
                .stream()
                .filter(s -> s.getName().toLowerCase().contains(lc))
                .collect(Collectors.toList());
        return result;
    }

    public BaseDAO<Student> getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(BaseDAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void clear() {
        studentDAO.deleteStore();
        studentDAO.createStore();
    }

}
