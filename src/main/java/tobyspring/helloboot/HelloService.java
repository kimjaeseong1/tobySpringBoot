package tobyspring.helloboot;

import org.springframework.stereotype.Component;


public interface HelloService {
    String sayHello(String name);

   default int countOf(String name){
       return 0;
   }
}
