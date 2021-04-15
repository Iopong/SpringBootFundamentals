package ttl.larku.service;

public abstract class AbstractService {

    protected final StudentService studentService;

    public AbstractService(StudentService studentService) {
        this.studentService = studentService;
    }
}
