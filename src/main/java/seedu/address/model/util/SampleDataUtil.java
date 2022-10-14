package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialTimeslot;
import seedu.address.model.tutorial.TutorialVenue;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"),
                    new ID("A0000000a"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Telegram("alexyeoh"),
                    new TutorialModule("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("A"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"),
                    new ID("A0000000B"),
                    new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Telegram("berniceyu"),
                    new TutorialModule("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade(""),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"),
                    new ID("A0000000C"),
                    new Phone("93210283"), new Email("charlotte@example.com"),
                    new Telegram("charlotte"),
                    new TutorialModule("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("A"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"),
                    new ID("A0000000D"),
                    new Phone("91031282"), new Email("lidavid@example.com"),
                    new Telegram("lidavid"),
                    new TutorialModule("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("A"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"),
                    new ID("A0000000I"),
                    new Phone("92492021"), new Email("irfan@example.com"),
                    new Telegram("irfan"),
                    new TutorialModule("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade("A"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"),
                    new ID("A0000000R"),
                    new Phone("92624417"), new Email("royb@example.com"),
                    new Telegram("royb"),
                    new TutorialModule("CS2103T"), new TutorialName("W17"),
                    new Attendance("0"), new Participation("0"), new Grade(""),
                    getTagSet("colleagues"))
        };
    }

    public static Tutorial[] getSampleTutorials() {
        return new Tutorial[] {
            new Tutorial(new TutorialName("W17"), new TutorialModule("CS2103T"),
                    new TutorialVenue("COM1-0203"), new TutorialTimeslot("16:00-18:00")),
            new Tutorial(new TutorialName("F01"), new TutorialModule("CS2103T"),
                    new TutorialVenue("COM1-0201"), new TutorialTimeslot("15:00-17:00")),
            new Tutorial(new TutorialName("F02"), new TutorialModule("CS2103T"),
                    new TutorialVenue("COM1-0202"), new TutorialTimeslot("10:00-11:00")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addPerson(sampleStudent);
        }
        for (Tutorial sampleTutorial: getSampleTutorials()) {
            sampleAb.addTutorial(sampleTutorial);
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
