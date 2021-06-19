package ecnu.jwss.main_controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"ecnu.jwss.*"})
@EntityScan("ecnu.jwss.dao")
@EnableJpaRepositories("ecnu.jwss.dao")
public class MainControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainControllerApplication.class, args);
	}

}
