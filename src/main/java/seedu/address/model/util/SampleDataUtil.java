package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.address.Address;
import seedu.address.model.desiredcharacteristics.DesiredCharacteristics;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.Description;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new PriceRange("200000 - 250000"), new DesiredCharacteristics("Bright; 5-Room"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new PriceRange("250000 - 280000"), new DesiredCharacteristics("Clean; Large"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new PriceRange("300000 - 400000"), new DesiredCharacteristics("Near MRT"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new PriceRange("500000 - 800000"), new DesiredCharacteristics("Near School"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new PriceRange("200000 - 250000"), new DesiredCharacteristics("Bishan"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new PriceRange("100000 - 150000"), new DesiredCharacteristics("Toa Payoh; Kid-Friendly"),
                getTagSet("colleagues"))
        };
    }

    public static Property[] getSampleProperties() {
        return new Property[]{
                new Property(new PropertyName("Residential College 4"), new Price("50000"),
                new Address("6 College Avenue East"), new Description("A place for NUS students to stay."),
                        getTagSet("istayhere")),
                new Property(new PropertyName("Tembusu College"), new Price("9999"),
                        new Address("26 College Avenue East"), new Description("A place for NUS students to stay."),
                        getTagSet("dorm")),
                new Property(new PropertyName("Peak Residence"), new Price("10000000"),
                        new Address("333 Thompson Road"),
                        new Description("latest freehold luxurious exclusive condominium."),
                        getTagSet("condo")),
                new Property(new PropertyName("Hut"), new Price("25000"),
                        new Address("25 Regent Road"),
                        new Description("new 3-room HDB flat"),
                        getTagSet("hdb"))

        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Property sampleProperty : getSampleProperties()) {
            sampleAb.addProperty(sampleProperty);
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

}
