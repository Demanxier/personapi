package one.digitalinnovation.demanxier.person.personapi.mapper;


import one.digitalinnovation.demanxier.person.personapi.dto.request.PersonDTO;
import one.digitalinnovation.demanxier.person.personapi.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper
public interface PersonMapper {


    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
