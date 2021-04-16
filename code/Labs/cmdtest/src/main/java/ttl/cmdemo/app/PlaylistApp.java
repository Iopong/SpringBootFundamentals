package ttl.cmdemo.app;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ttl.cmdemo.jconfig.TrackerConfig;
import ttl.cmdemo.domain.Track;
import ttl.cmdemo.service.TrackService;

public class PlaylistApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext();
        appContext.getEnvironment().setActiveProfiles("development");
        //appContext.getEnvironment().setActiveProfiles("production");
        appContext.register(TrackerConfig.class);
        appContext.refresh();

        TrackService ts = appContext.getBean("trackService", TrackService.class);

        List<Track> tracks = ts.getAllTracks();
        tracks.forEach(System.out::println);


    }
}
