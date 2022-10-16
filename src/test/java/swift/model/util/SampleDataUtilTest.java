package swift.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import swift.model.AddressBook;
import swift.model.person.Person;

class SampleDataUtilTest {
    @Test
    public void getSampleAddressBook() {
        AddressBook addressBook = new AddressBook();
        for (Person samplePerson : SampleDataUtil.getSamplePersons()) {
            addressBook.addPerson(samplePerson);
        }
        assertEquals(addressBook, SampleDataUtil.getSampleAddressBook());
    }
}