package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationTimeslot;
import seedu.address.model.consultation.ConsultationVenue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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

    public static Consultation[] getSampleConsultations() {
        return new Consultation[] {
            new Consultation(new ConsultationName("Anna"), new ConsultationModule("CS2103T"),
                        new ConsultationVenue("COM1-0203"), new ConsultationTimeslot("16:00-18:00"),
                        new ConsultationDescription("Review past year paper")),
            new Consultation(new ConsultationName("Tom"), new ConsultationModule("CS2103T"),
                        new ConsultationVenue("COM1-0201"), new ConsultationTimeslot("15:00-17:00"),
                        new ConsultationDescription("Review IP code quality")),
            new Consultation(new ConsultationName("John"), new ConsultationModule("CS2103T"),
                        new ConsultationVenue("COM1-0202"), new ConsultationTimeslot("10:00-11:00"),
                        new ConsultationDescription("Clear doubts about lecture")),
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

        for (Consultation sampleConsultation: getSampleConsultations()) {
            sampleAb.addConsulation(sampleConsultation);
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
