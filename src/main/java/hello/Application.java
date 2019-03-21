package hello;

import hello.domain.Contact;
import hello.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(ContactRepository repository) {
		return (args) -> {
			repository.save(new Contact("Kim", "Chang", "9609041213", "9709041213", "vk.com/1", "@telegramm1", "skipe1"));
			repository.save(new Contact("Melony", "Konstantin", "9609041213", "9709041213", "vk.com/2", "@telegramm2", "skipe2"));
			repository.save(new Contact("Bess", "O'Donnell", "9609041213", "9709041213", "vk.com/3", "@telegramm3", "skipe3"));
			repository.save(new Contact("Kelly", "Berry", "9609041213", "9709041213", "vk.com/4", "@telegramm4", "skipe4"));
		};
	}
}
