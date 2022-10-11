package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                    new Name("Alex Yeoh"),
                    new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Gender("Male"),
                    new GraduationDate("05-2022"),
                    new Cap(3.5, 4.0),
                    new University("National University of Singapore"),
                    new Major("Computer Science"),
                    new Id("169277"),
                    new Title("IT Software Engineer (6 months internship)"),
                    getTagSet("offered")),
            new Person(
                    new Name("Bernice Yu"),
                    new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Gender("Female"),
                    new GraduationDate("05-2024"),
                    new Cap(5.0, 5.0),
                    new University("Nanyang Technological University"),
                    new Major("Computer Engineering"),
                    new Id("169277"),
                    new Title("IT Software Engineer (6 months internship)"),
                    getTagSet()),
            new Person(
                    new Name("Charlotte Oliveiro"),
                    new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Gender("Female"),
                    new GraduationDate("01-2025"),
                    new Cap(3, 5),
                    new University("Oxford University"),
                    new Major("Business Analytics"),
                    new Id("165997"),
                    new Title("[Campus Recruitment] Backend Engineer Intern (Dec'23 - May'23)"),
                    getTagSet("assessment", "rejected")),
            new Person(
                    new Name("David Li"),
                    new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Gender("Male"),
                    new GraduationDate("07-2026"),
                    new Cap(4.97, 5),
                    new University("Singapore Management University"),
                    new Major("Chemical Engineering"),
                    new Id("169277"),
                    new Title("IT Software Engineer (6 months internship)"),
                    getTagSet("KIV")),
            new Person(
                    new Name("Irfan Ibrahim"),
                    new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Gender("Male"),
                    new GraduationDate("12-2020"),
                    new Cap(3.58, 4.0),
                    new University("Singapore University of Technology and Design"),
                    new Major("Civil Engineering"),
                    new Id("183698"),
                    new Title("Data Scientist Intern"),
                    getTagSet("final interview", "rejected")),
            new Person(
                    new Name("Roy Balakrishnan"),
                    new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Gender("Male"),
                    new GraduationDate("05-2024"),
                    new Cap(4.55, 5.0),
                    new University("Temasek Polytechnic"),
                    new Major("Electrical Engineering"),
                    new Id("174059"),
                    new Title("Software Engineering Intern Internship, May - August 2023"),
                    getTagSet("offer rejected"))
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

}
