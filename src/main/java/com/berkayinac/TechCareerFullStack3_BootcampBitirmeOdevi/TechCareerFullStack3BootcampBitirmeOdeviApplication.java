package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Auditing Aktif etmek
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBeanMethod")
@SpringBootApplication(exclude = {
		//SecurityAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
}
)
public class TechCareerFullStack3BootcampBitirmeOdeviApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechCareerFullStack3BootcampBitirmeOdeviApplication.class, args);
	}

}
