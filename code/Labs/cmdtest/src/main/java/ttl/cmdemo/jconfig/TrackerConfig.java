package ttl.cmdemo.jconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import ttl.cmdemo.dao.BaseDAO;
import ttl.cmdemo.dao.inmemory.InMemoryTrackDAO;
import ttl.cmdemo.dao.jpahibernate.JPATrackDAO;
import ttl.cmdemo.domain.Track;
import ttl.cmdemo.service.TrackService;

import java.util.List;

@Configuration
@ComponentScan({"ttl.cmdemo.service", "ttl.cmdemo.dao"})
public class TrackerConfig {

    @Autowired
    private Environment env;

    private TrackerTestDataConfig testDataProducer = new TrackerTestDataConfig();

    @Bean(name = "trackDAO")
    //@Profile("development")
    public BaseDAO<Track> trackDAO() {
        return testDataProducer.trackDAOWithInitData();
    }

    @Bean
    @Profile("production")
    public BaseDAO<Track> trackDAOJpa() {
        return jpaTrackDAOWithTestData();
    }

    @Bean
    public BaseDAO<Track> inMemoryTrackDAO() {
       return new InMemoryTrackDAO();
    }

    @Bean
    public BaseDAO<Track> jpaTrackDAOWithTestData() {
        JPATrackDAO dao = new JPATrackDAO();
        List<Track> fakeTracks = testDataProducer.tracks();
        fakeTracks.forEach(dao::create);

        return dao;
    }

    @Bean
    public BaseDAO<Track> jpaTrackDAO() {
        JPATrackDAO dao = new JPATrackDAO();
        return dao;
    }

    @Bean
    public TrackService trackService() {
        TrackService ss = new TrackService();
        ss.setTrackDAO(trackDAO());

        return ss;
    }
}