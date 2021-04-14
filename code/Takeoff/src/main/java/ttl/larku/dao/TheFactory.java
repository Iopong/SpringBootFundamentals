package ttl.larku.dao;

import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.dao.jpa.JpaStudentDAO;
import ttl.larku.service.StudentService;

import java.util.ResourceBundle;

public class TheFactory {

    public static StudentDAO getStudentDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
        String profile = bundle.getString("larku.profile.active");
        switch(profile) {
            //if dev
            case "dev":
                return new InMemoryStudentDAO();
            //else if prod
            case "prod":
                return new JpaStudentDAO();
            default:
                throw new RuntimeException("Unknown profile: " + profile);
        }
    }

    public static StudentService getStudentService() {
        //StudentDAO dao = new InMemoryStudentDAO();
        StudentDAO dao = getStudentDAO();

        StudentService ss = new StudentService(dao);
        return ss;
    }
}
