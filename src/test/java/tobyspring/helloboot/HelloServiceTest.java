package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);
        String ret = helloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("HelloTest");
    }




private static HelloRepository helloRepositoryStub = new HelloRepository() {
    @Override
    public Hello findHello(String name) {
        return null;
    }

    @Override
    public void increaseCount(String name) {

    }
};




    @Test
    void helloDecorator(){
        HelloDecorator decorator = new HelloDecorator(name -> name);

       String ret =  decorator.sayHello("Test");

       Assertions.assertThat(ret).isEqualTo("*Test*");
    }

    @HelloBootTest
    public static class HelloServiceCountTest {

        @Autowired
        HelloService helloService;
        @Autowired
        HelloRepository helloRepository;


        @Test
        void sayHelloIncreaseCount(){
            IntStream.rangeClosed(1,10).forEach(count -> {
                helloService.sayHello("Toby");
                Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(count);

            });

        }
    }
}

