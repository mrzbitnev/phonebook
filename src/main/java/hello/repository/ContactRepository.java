package hello.repository;

import hello.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("from Contact c " +
			"where concat(c.firstName, ' ', c.lastName, ' ', c.phoneNumber," +
			"' ', c.contactField1, ' ',  c.contactField2, ' ', c.contactField3, ' ', c.contactField4)" +
			"like concat('%', :name, '%') ")
	List<Contact> findByName(@Param("name") String name);
}
