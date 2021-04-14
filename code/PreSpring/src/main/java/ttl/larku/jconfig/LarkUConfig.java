package ttl.larku.jconfig;

import org.springframework.context.annotation.*;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

@Configuration
@ComponentScan({"ttl.larku.service", "ttl.larku.dao"})
public class LarkUConfig {

//    <bean id="inMemoryStudentDAO" class="ttl.larku.dao.inmemory.InMemoryStudentDAO" scope="prototype"/>

//    <bean id="inMemoryStudentDAO" class="ttl.larku.dao.inmemory.InMemoryStudentDAO"/>
    @Bean(name = "studentDAO")
    public BaseDAO<Student> inMemoryStudentDAO() {
        return new InMemoryStudentDAO();
    }

//    <bean id="studentService" class="ttl.larku.service.StudentService">
//        <property name="studentDAO" ref="inMemoryStudentDAO"/>
//    </bean>

    @Bean
    public StudentService studentService() {
        StudentService ss = new StudentService();

        BaseDAO<Student> sd = inMemoryStudentDAO();

        ss.setStudentDAO(sd);

        return ss;
    }
}