package mcgillar.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import mcgillar.backend.configuration.startupScripts.EntryStartup;

@SpringBootApplication
@AllArgsConstructor
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EntryStartup runner) {
		return args -> {
			runner.startup();
		};
	}
}
