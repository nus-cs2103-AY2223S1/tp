package swift.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import swift.model.AddressBook;
import swift.model.person.Person;

class SampleDataUtilTest {
    @Test
    public void getSampleAddressBook_success() {
        AddressBook addressBook = new AddressBook();
        addressBook.setPersons(SampleDataUtil.getSamplePersons());
        addressBook.setTasks(SampleDataUtil.getSampleTasks());
        addressBook.setBridges(SampleDataUtil.getSamplePersonTaskBridge());

        assertEquals(addressBook, SampleDataUtil.getSampleAddressBook());
    }
}
