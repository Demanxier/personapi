package one.digitalinnovation.demanxier.person.personapi.repositories;

import one.digitalinnovation.demanxier.person.personapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
