package ttl.larku.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.domain.Student.Status;
import ttl.larku.jconfig.LarkUConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * jUnit has no concept of autowire in testing.
 * We use ExtendWith to tell it to extend with Spring, and
 * use a configuration class for the objects that we need for
 * autowiring.
 *
 * Spring will create the context on creation and keep re-using
 * over and over. It will not reset the autowiring. Keep in mind
 * that creating a context is quite expensive.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LarkUConfig.class})
public class StudentDAOTest {

    private String name1 = "Bloke";
    private String name2 = "Blokess";
    private String newName = "Karl Jung";
    private String phoneNumber1 = "290 298 4790";
    private String phoneNumber2 = "3838 939 93939";
    private Student student1;
    private Student student2;

    private InMemoryStudentDAO dao;

    @BeforeEach
    public void setup() {
        dao = new InMemoryStudentDAO();
        dao.deleteStore();
        dao.createStore();

        student1 = new Student(name1, phoneNumber1, Status.FULL_TIME);
        student2 = new Student(name2, phoneNumber2, Status.HIBERNATING);

        dao.create(student1);
        dao.create(student2);
    }


    @Test
    public void testGetAll() {
        List<Student> students = dao.getAll();
        assertEquals(2, students.size());
    }

    @Test
    public void testCreate() {

        int newId = dao.create(student1).getId();

        Student resultstudent = dao.get(newId);

        assertEquals(newId, resultstudent.getId());
    }

    @Test
    public void testUpdate() {
        int newId = dao.create(student1).getId();

        Student resultStudent = dao.get(newId);

        assertEquals(newId, resultStudent.getId());

        resultStudent.setName(newName);
        dao.update(resultStudent);

        resultStudent = dao.get(resultStudent.getId());
        assertEquals(newName, resultStudent.getName());
    }

    @Test
    public void testDelete() {
        int id1 = dao.create(student1).getId();

        Student resultStudent = dao.get(id1);
        assertEquals(resultStudent.getId(), id1);

        int beforeSize = dao.getAll().size();

        dao.delete(resultStudent);

        resultStudent = dao.get(id1);

        assertEquals(beforeSize - 1, dao.getAll().size());
        assertEquals(null, resultStudent);

    }

}
