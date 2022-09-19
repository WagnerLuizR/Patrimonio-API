package bri.ifsp.edu.br.patrimonioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class PatrimonioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatrimonioApiApplication.class, args);
	}

}
