package ttl.larku.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.List;

//GET, PUT, POST, DELETE

//GET retrieve - safe idempotent
//PUT update - idempotent
//DELETE delete - idempotent

//POST insert or anything else you feel like doing

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
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
       Student student = studentService.getStudent(id);
       if(student == null) {
           return ResponseEntity.status(404).body("No Student with ID: " + id);
           //return ResponseEntity.badRequest().body("No Student with ID: " + id);
       }
       return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        //return ResponseEntity.created(newResource).body(newStudent);
        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        boolean result = studentService.deleteStudent(id);
        if(result) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().body("Problem deleting Student with id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        boolean result = studentService.updateStudent(student);
        if(result) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().body("Problem updating Student with id: " + id);
        }
    }


}
