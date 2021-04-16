package ttl.cmdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

//@SpringBootApplication(scanBasePackages = {"ttl.cmdemo"})
@SpringBootApplication
public class CmdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(CmdemoApplication.class, args);
    }

}


@Component
class MyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyRunner called");
    }
}