package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new Phone("87438807")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new Phone("99272758")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), new Phone("93210283")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), new Phone("91031282")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new Phone("92492021")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new Phone("92624417")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }
}
