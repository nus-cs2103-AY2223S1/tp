package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Logger logger = LogsCenter.getLogger(SampleDataUtil.class);

    private static Person alex = new Person(new Name("Alex Yeoh"),
            new Phone("87438807"), new Email("alexyeoh@example.com"),
            new Address("Blk 30 Geylang Street 29, #06-40"),
            getTagSet("friends"));
    private static Person bernice = new Person(new Name("Bernice Yu"),
            new Phone("99272758"), new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            getTagSet("colleagues", "friends"));
    private static Person charlotte = new Person(new Name("Charlotte Oliveiro"),
            new Phone("93210283"), new Email("charlotte@example.com"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            getTagSet("neighbours"));
    private static Person david = new Person(new Name("David Li"),
            new Phone("91031282"), new Email("lidavid@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            getTagSet("family"));
    private static Person irfan = new Person(new Name("Irfan Ibrahim"),
            new Phone("92492021"), new Email("irfan@example.com"),
            new Address("Blk 47 Tampines Street 20, #17-35"),
            getTagSet("classmates"));
    private static Person roy = new Person(new Name("Roy Balakrishnan"),
            new Phone("92624417"), new Email("royb@example.com"),
            new Address("Blk 45 Aljunied Street 85, #11-31"),
            getTagSet("colleagues"));

    public static Person[] getSamplePersons() {
        return new Person[] {
            alex,
            bernice,
            charlotte,
            david,
            irfan,
            roy
        };
    }

    public static Meeting[] getSampleMeetings() throws ParseException, java.text.ParseException {
        ArrayList<Person> p1 = new ArrayList<>();
        ArrayList<Person> p2 = new ArrayList<>();
        ArrayList<Person> p3 = new ArrayList<>();
        ArrayList<Person> p4 = new ArrayList<>();
        ArrayList<Person> p5 = new ArrayList<>();

        p1.add(alex);

        p2.add(bernice);
        p2.add(charlotte);

        p3.add(david);
        p3.add(irfan);
        p3.add(roy);

        p4.add(david);
        p4.add(alex);
        p4.add(charlotte);
        p4.add(bernice);

        p5.add(roy);
        p5.add(alex);
        p5.add(david);
        p5.add(bernice);
        p5.add(charlotte);

        return new Meeting[] {
            new Meeting(p1, "CS2103", "Saturday, 2 April 2022 03:00 PM", "COM3"),
            new Meeting(p2, "GEA1000", "Friday, 10 December 2021 08:00 AM", "UTOWN"),
            new Meeting(p3, "Lunch", "Wednesday, 2 February 2022 05:00 PM", "KR MRT"),
            new Meeting(p4, "Dinner", "Wednesday, 30 November 2022 05:00 PM", "Fine Food"),
            new Meeting(p5, "Supper", "Saturday, 1 October 2022 12:00 AM", "Mcdonalds")
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }
    public static ReadOnlyMeetingList getSampleMeetingList() {
        MeetingList sampleML = new MeetingList();
        Meeting[] m;
        try {
            m = getSampleMeetings();
            for (Meeting sampleMeeting :m) {
                sampleML.addMeeting(sampleMeeting);
            }
        } catch (ParseException | java.text.ParseException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
        }

        return sampleML;
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
