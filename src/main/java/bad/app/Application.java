package bad.app;

import bad.app.domain.User;
import bad.app.domain.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public InitializingBean initData(UserRepository userRepository) {
        return () -> {
            userRepository.save(new User(1L, "admin", "admin", "opis admina"));
            userRepository.findAll().forEach(System.err::println);
        };
    }
}
