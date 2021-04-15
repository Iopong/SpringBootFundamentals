package ttl.larku.didemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

interface Trick {
    public void doTrick();
}

//@Component("trick3")
//@Qualifier("us-west")
//@Order(10)
class OtherTrick implements Trick {

    public void onlyInOtherTrick() {

    }

    @Override
    public void doTrick() {
        System.out.println("Card trick");
    }
}

@Component
@Profile("dev")
//@Qualifier("us-east")
//@Order(1)
class Trick1 implements Trick {

    @Override
    public void doTrick() {
        System.out.println("Doing somersault");
    }
}

@Component
@Profile("prod")
//@Primary
//@Qualifier("us-east")
//@Order(20)
class Trick2 implements Trick {

    @Override
    public void doTrick() {
        System.out.println("Doing handstand");
    }
}

@Component
class Circus {
    //   @Resource(name = "trick1")


    @Autowired
//    @Resource(name = "theTrick")
    private Trick trick;

//    @Autowired
//    @Qualifier("us-east")
    //private Trick trick2;
//    private Trick trick;
//    private List<Trick> tricks;


    public void runShow() {
        trick.doTrick();
//        tricks.forEach(Trick::doTrick);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.scan("ttl.larku.didemo");
        context.refresh();

        Circus circus = context.getBean("circus", Circus.class);
        circus.runShow();
    }
}
