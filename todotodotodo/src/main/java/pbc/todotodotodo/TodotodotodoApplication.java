package pbc.todotodotodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodotodotodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodotodotodoApplication.class, args);
    }

}
