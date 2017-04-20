package asw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootApplication
//@EntityScan(basePackages="asw.persistence.model.impl")
@ComponentScan(basePackages="asw")
@EnableJpaRepositories(basePackages="asw.persistence.repositories")
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }

}