package com.bsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.bsl.model.Employee;
import com.bsl.repo.EmployeeRepository;

import java.math.BigDecimal;
import java.time.Instant;
@SpringBootApplication
public class CachingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingAppApplication.class, args);
	}
    // Seed some sample data on startup
    @Bean
    CommandLineRunner dataLoader(EmployeeRepository repo) {
        return args -> {
            repo.save(new Employee(null, "Asha Nair", "asha.nair@example.com", "Engineering",
                    new BigDecimal("120000"), Instant.now(), Instant.now()));
            repo.save(new Employee(null, "Ravi Verma", "ravi.verma@example.com", "HR",
                    new BigDecimal("80000"), Instant.now(), Instant.now()));
            repo.save(new Employee(null, "Meera Das", "meera.das@example.com", "Finance",
                    new BigDecimal("95000"), Instant.now(), Instant.now()));
        };
    }
}
