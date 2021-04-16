package ttl.larku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

//GET, PUT, POST, DELETE

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    // http://xyz.com/student/2
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
       Student student = studentService.getStudent(id);
       return student;
    }

}
