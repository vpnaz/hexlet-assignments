package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.BeanDefinition;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Daytime daytime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        return hour >= 6 && hour <= 22 ? new Day() : new Night();
    }
    // END
}
