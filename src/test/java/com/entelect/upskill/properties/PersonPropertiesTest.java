package com.entelect.upskill.properties;

import com.entelect.upskill.concatenation.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ActiveProfiles(profiles = {"test"})
@SpringBootTest
public class PersonPropertiesTest {

    @Autowired
    private  PersonProperties personProperties;

    @Test
    @DisplayName("Given the upskill-properties , "+
                 "When getPeople method is invoked ,"+
                 "Then I expect the list to be not empty ")
    void getAllPeople(){
        //Given && When
        List<Person>  people = personProperties.getPeople();

        //Then
        assertFalse(people.isEmpty());
    }

    @Test
    @DisplayName("Given the upskill-properties ,"+
                  "When getPeople method is invoked ,"+
                   "Then I expect all the people names to be fetched")
    void  getPeopleNames(){
        // Given && When
        List<Person>  people = personProperties.getPeople();

        //Then
        assertEquals("Peter",people.get(0).getName());
        assertEquals("Ryan",people.get(1).getName());
    }
}
