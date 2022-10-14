package seedu.address.model.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.util.Pair;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                getContacts(
                    new Pair<>(ContactType.PHONE, "87438807"),
                    new Pair<>(ContactType.EMAIL, "alexyeoh@example.com")
                )
            ),
            new Person(new Name("Bernice Yu"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"),
                getContacts(
                    new Pair<>(ContactType.PHONE, "99272758"),
                    new Pair<>(ContactType.EMAIL, "berniceyu@example.com")
                )
            ),
            new Person(new Name("Charlotte Oliveiro"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"),
                getContacts(
                    new Pair<>(ContactType.PHONE, "93210283"),
                    new Pair<>(ContactType.EMAIL, "charlotte@example.com")
                )
            ),
            new Person(new Name("David Li"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"),
                getContacts(
                    new Pair<>(ContactType.PHONE, "91031282"),
                    new Pair<>(ContactType.EMAIL, "lidavid@example.com")
                )
            ),
            new Person(new Name("Irfan Ibrahim"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                getContacts(
                    new Pair<>(ContactType.PHONE, "92492021"),
                    new Pair<>(ContactType.EMAIL, "irfan@example.com")
                )
            ),
            new Person(new Name("Roy Balakrishnan"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                getContacts(
                    new Pair<>(ContactType.PHONE, "92624417"),
                    new Pair<>(ContactType.EMAIL, "royb@example.com")
                )
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

    public static Map<ContactType, Contact> getContacts(Pair<ContactType, String>... contacts) {
        return Arrays.stream(contacts)
            .map(entry -> Contact.of(entry.getKey(), entry.getValue()))
            .collect(Collectors.toMap(Contact::getContactType, Function.identity()));
    }

}
