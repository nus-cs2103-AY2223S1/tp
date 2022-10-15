package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.datetime.Datetime;
import seedu.address.model.datetime.WeeklyTimeslot;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.ta.TeachingAssistant;
import seedu.address.model.ta.TeachingAssistantId;
import seedu.address.model.ta.TeachingAssistantName;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialVenue;

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

    public static Reminder[] getSampleReminders() {
        return new Reminder[] {
            new Reminder(new ReminderName("Mark Midterms"), new Datetime("20:00"),
                        new ReminderDescription("300 papers to mark")),
            new Reminder(new ReminderName("Send email to TAs"), new Datetime("21:00"),
                        new ReminderDescription("Erratum in midterms")),
            new Reminder(new ReminderName("Meeting with Prof Lee"), new Datetime("22:00"),
                        new ReminderDescription("Discuss incoming finals for CS2201")),
        };
    }

    public static Tutorial[] getSampleTutorials() {
        return new Tutorial[] {
            new Tutorial(new TutorialName("W17"), new TutorialModule("CS2103T"),
                    new TutorialVenue("COM1-0203"),
                    new WeeklyTimeslot("1", "16:00", "18:00")),
            new Tutorial(new TutorialName("F01"), new TutorialModule("CS2103T"),
                    new TutorialVenue("COM1-0201"),
                    new WeeklyTimeslot("2", "15:00", "17:00")),
            new Tutorial(new TutorialName("F02"), new TutorialModule("CS2103T"),
                    new TutorialVenue("COM1-0202"),
                    new WeeklyTimeslot("3", "10:00", "11:00")),
        };
    }

    public static TeachingAssistant[] getSampleTeachingAssistants() {
        return new TeachingAssistant[] {
            new TeachingAssistant(new TeachingAssistantName("John"), new TeachingAssistantId("A0000000A")),
            new TeachingAssistant(new TeachingAssistantName("Mary"), new TeachingAssistantId("A0123456B")),
            new TeachingAssistant(new TeachingAssistantName("Tom"), new TeachingAssistantId("A0987654C")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Tutorial sampleTutorial: getSampleTutorials()) {
            sampleAb.addTutorial(sampleTutorial);
        }
        for (TeachingAssistant sampleTeachingAssistant: getSampleTeachingAssistants()) {
            sampleAb.addTeachingAssistant(sampleTeachingAssistant);
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
