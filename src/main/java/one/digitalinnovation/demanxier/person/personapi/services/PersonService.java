package one.digitalinnovation.demanxier.person.personapi.services;

import lombok.AllArgsConstructor;
import one.digitalinnovation.demanxier.person.personapi.mapper.PersonMapper;
import one.digitalinnovation.demanxier.person.personapi.dto.request.PersonDTO;
import one.digitalinnovation.demanxier.person.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.demanxier.person.personapi.entities.Person;
import one.digitalinnovation.demanxier.person.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.demanxier.person.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    //private final PersonMapper personMapper;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID");
    }

    public List<PersonDTO> listAll(){
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }


    public MessageResponseDTO updateById(long id, PersonDTO personDTO) throws PersonNotFoundException{
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Update person with ID.");

    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message){
        return MessageResponseDTO.builder().message(message + id).build();
    }
}
