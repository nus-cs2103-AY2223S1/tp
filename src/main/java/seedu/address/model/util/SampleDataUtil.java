package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
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
                new Address("Blk 30 Geylang Street 29, #06-40"), new Birthday("1 January 2000"),
                getTagSet("friends"), getReminderList("Wish Alex Yeoh happy birthday!",
                "1-01-2000")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Birthday("12 January 2000"),
                getTagSet("colleagues", "friends"), getReminderList("Wish Bernice Yu happy birthday!",
                    "12-01-2000")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Birthday("1 March 2000"),
                getTagSet("neighbours"), getReminderList("Wish Charlotte Oliveiro happy birthday!",
                    "1-03-2000")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Birthday("1 May 2000"),
                getTagSet("family"), getReminderList("Wish David Li happy birthday!",
                    "1-05-2000")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Birthday("25 June 2004"),
                getTagSet("classmates"), getReminderList("Wish Irfan Ibrahim happy birthday!",
                    "25-06-2004")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Birthday("1 October 1989"),
                getTagSet("colleagues"), getReminderList("Wish Roy Balakrishnan happy birthday!",
                    "1-10-1989")),
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
     * Returns a reminder list containing the list of reminders given.
     */
    public static Set<Reminder> getReminderList(String... strings) {
        Set<Reminder> reminderArrayList = new HashSet<>();
        for (int i = 0; i < strings.length; i += 2) {
            reminderArrayList.add(new Reminder(strings[i], strings[i + 1]));
        }
        return reminderArrayList;
    }

}
