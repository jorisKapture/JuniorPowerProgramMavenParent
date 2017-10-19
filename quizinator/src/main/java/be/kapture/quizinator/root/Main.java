package be.kapture.quizinator.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("be.kapture.quizinator.root")
public class Main {

    public static void main(String args[]){
        SpringApplication.run(Main.class, args);
    }
}
