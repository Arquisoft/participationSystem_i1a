package asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan("asw.persistence.model.impl")
@ComponentScan(basePackages="asw")
@EnableJpaRepositories("asw.persistence.repositories")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}