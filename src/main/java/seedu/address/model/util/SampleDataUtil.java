package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Gender("M"), getTagSet("friends"), new Location("Chess club room"),
                new GithubUsername("alexy", true), getModuleCodeSet("CS4534", "CS5234"),
                new Year("1")),
            new Professor(new Name("Wong Tin Lok"), new ModuleCode("CS1231S"),
                new Phone("91031282"), new Email("wongtk@example.com"),
                new Gender("M"),
                getTagSet("family"), new Location("COM2 LT4"), new GithubUsername("Wongwong", true),
                new Rating("5"), new Specialisation("Discrete Math"),
                new OfficeHour("MONDAY, 03:00 PM - 05:00 PM", true)),
            new TeachingAssistant(new Name("Irfan Ibrahim"), new ModuleCode("CS2100"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Gender("M"),
                getTagSet("testing"), new Location("COM2-0210"), new GithubUsername("irfanib", true),
                new Rating("4")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Gender("M"),
                getTagSet("colleagues", "friends"), new Location("UTown"),
                new GithubUsername("", false), getModuleCodeSet("CS4534", "CS5234"),
                new Year("2")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Gender("F"),
                getTagSet("neighbours"), new Location("NUS"), new GithubUsername("", false),
                getModuleCodeSet("CS4534", "CS5234"), new Year("4")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Gender("M"), getTagSet("colleagues"), new Location("Research Lab"),
                new GithubUsername("", false), getModuleCodeSet("CS4534", "CS5234"),
                new Year("3"))
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
     * Returns a moduleCode set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(ModuleCode::new)
                .collect(Collectors.toSet());
    }

}
