package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.util.Pair;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.CriticalIllnessInsurance;
import seedu.address.model.person.DisabilityInsurance;
import seedu.address.model.person.Email;
import seedu.address.model.person.HealthInsurance;
import seedu.address.model.person.LifeInsurance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reminder;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Birthday("01-01-2000"),
                getReminderSet(new Pair<>("Wish Alex Yeoh Happy Birthday!", "01-01-2023")),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Birthday("12-01-2000"),
                new HealthInsurance(true), new DisabilityInsurance(false),
                new CriticalIllnessInsurance(true), new LifeInsurance(true),
                getReminderSet(new Pair<>("Wish Bernice Yu Happy Birthday!", "12-01-2023")),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Birthday("01-03-2000"),
                new HealthInsurance(false), new DisabilityInsurance(false),
                new CriticalIllnessInsurance(true), new LifeInsurance(true),
                getReminderSet(new Pair<>("Wish Charlotte Oliveiro Happy Birthday!", "01-03-2023")),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Birthday("01-05-2000"),
                new HealthInsurance(true), new DisabilityInsurance(true),
                new CriticalIllnessInsurance(true), new LifeInsurance(true),
                getReminderSet(new Pair<>("Wish David Li Happy Birthday!", "01-05-2023")),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Birthday("25-06-2004"),
                new HealthInsurance(false), new DisabilityInsurance(false),
                new CriticalIllnessInsurance(false), new LifeInsurance(false),
                getReminderSet(new Pair<>("Wish Irfan Ibrahim Happy Birthday!", "25-06-2023")),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Birthday("01-10-1989"),
                getReminderSet(new Pair<>("Wish Roy Balakrishnan Happy Birthday!", "01-10-2023")),
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

    /**
     * Returns a reminder set containing the list of reminders given.
     */
    @SafeVarargs
    public static Set<Reminder> getReminderSet(Pair<String, String>... reminders) {
        return Arrays.stream(reminders)
                .map(reminder -> new Reminder(reminder.getKey(), reminder.getValue()))
                .collect(Collectors.toSet());
    }
}
