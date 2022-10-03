package tracko.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import tracko.model.AddressBook;
import tracko.model.ReadOnlyAddressBook;
import tracko.model.ReadOnlyTrackO;
import tracko.model.TrackO;
import tracko.model.order.Order;
import tracko.model.person.Address;
import tracko.model.person.Email;
import tracko.model.person.Name;
import tracko.model.person.Person;
import tracko.model.person.Phone;
import tracko.model.tag.Tag;

import javax.sound.midi.Track;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
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

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order("Alex Yeoh", "87438807", "alexyeoh@example.com",
                "Blk 30 Geylang Street 29, #06-40",
                "keychain", 3),
            new Order("Bernice Yu", "99272758", "berniceyu@example.com",
                "Blk 30 Lorong 3 Serangoon Gardens, #07-18",
                "bolster", 1),
            new Order("Charlotte Oliveiro", "93210283", "charlotte@example.com",
                "Blk 11 Ang Mo Kio Street 74, #11-04",
                "pillow", 1),
            new Order("David Li", "91031282", "lidavid@example.com",
                "Blk 436 Serangoon Gardens Street 26, #16-43",
                "mattress", 2)
        };
    }

    public static ReadOnlyTrackO getSampleTrackO() {
        TrackO sampleTrackO = new TrackO();
        for (Order sampleOrder : getSampleOrders()) {
            sampleTrackO.addOrder(sampleOrder);
        }
        return sampleTrackO;
    }
}
