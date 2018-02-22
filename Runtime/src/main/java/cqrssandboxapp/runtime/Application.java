package cqrssandboxapp.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public Application(){

        CommandBus.getInstance();
        QueryBus.getInstance();
        EventBus.getInstance();
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}