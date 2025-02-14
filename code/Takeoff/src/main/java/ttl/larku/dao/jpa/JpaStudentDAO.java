package ttl.larku.dao.jpa;

import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JpaStudentDAO implements StudentDAO{

    private Map<Integer, Student> students = new HashMap<Integer, Student>();
    private static AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public void update(Student updateObject) {
        if (students.containsKey(updateObject.getId())) {
            students.put(updateObject.getId(), updateObject);
        }
    }

    public void delete(Student student) {
        students.remove(student.getId());
    }

    public Student create(Student newObject) {
        //Create a new Id
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);
        newObject.setName("Jpa" + newObject.getName());
        students.put(newId, newObject);

        return newObject;
    }

    public Student get(int id) {
        return students.get(id);
    }

    public List<Student> getAll() {
        return new ArrayList<Student>(students.values());
    }

    public void deleteStore() {
        students = null;
    }

    public void createStore() {
        students = new HashMap<Integer, Student>();
        nextId = new AtomicInteger(0);
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }
}
