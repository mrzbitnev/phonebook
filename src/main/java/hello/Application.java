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

//	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(ContactRepository repository) {
		return (args) -> {
			// save a couple of customers
//			repository.save(new Contact("Jack", "Bauer"));
//			repository.save(new Contact("Chloe", "O'Brian"));
//			repository.save(new Contact("Kim", "Bauer"));
//			repository.save(new Contact("David", "Palmer"));
//			repository.save(new Contact("Michelle", "Dessler"));

//			// fetch all customers
//			log.info("Customers found with findByLastNameStartsWithIgnoreCase():");
//			log.info("-------------------------------");
//			for (Contact contact : repository.findAll()) {
//				log.info(contact.toString());
//			}
//			log.info("");
//
//			// fetch an individual contact by ID
//			Contact contact = repository.findById(1L).get();
//			log.info("Contact found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(contact.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Contact found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Contact bauer : repository
//					.findByLastNameStartsWithIgnoreCase("Bauer")) {
//				log.info(bauer.toString());
//			}
//			log.info("");
		};
	}

}
