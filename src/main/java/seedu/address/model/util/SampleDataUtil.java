package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                    new PersonId(0),
                    new Name("Alex Yeoh"),
                    new Email("alexyeoh@example.com"),
                    new Phone("87438807"),
                    new InternshipId(0),
                    getTagSet("Manager"),
                    null),
            new Person(
                    new PersonId(1),
                    new Name("Bernice Yu"),
                    new Email("berniceyu@example.com"),
                    new Phone("99272758"),
                    null,
                    getTagSet("Supervisor"),
                    new Company("Meta")),
            new Person(
                    new PersonId(2),
                    new Name("Charlotte Oliveiro"),
                    new Email("charlotte@example.com"),
                    new Phone("93210283"),
                    null,
                    getTagSet("HR"),
                    null),
            new Person(
                    new PersonId(3),
                    new Name("David Li"),
                    new Email("lidavid@example.com"),
                    new Phone("91031282"),
                    null,
                    getTagSet("HR"),
                    null),
            new Person(
                    new PersonId(4),
                    new Name("Irfan Ibrahim"),
                    new Email("irfan@example.com"),
                    new Phone("92492021"),
                    null,
                    getTagSet("HR"),
                    null),
            new Person(
                    new PersonId(5),
                    new Name("Roy Balakrishnan"),
                    new Email("royb@example.com"),
                    new Phone("92624417"),
                    new InternshipId(1),
                    getTagSet("HR"),
                    null)
        };
    }

    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(
                    new InternshipId(0),
                    new CompanyName("company ABC123"),
                    new InternshipRole("frontend engineer"),
                    new InternshipStatus(InternshipStatus.State.ACCEPTED),
                    new PersonId(0),
                    null),
            new Internship(
                    new InternshipId(1),
                    new CompanyName("Google"),
                    new InternshipRole("data analyst"),
                    new InternshipStatus(InternshipStatus.State.PENDING),
                    new PersonId(5),
                    new InterviewDate("2022-11-11 11:11")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Internship sampleInternship : getSampleInternships()) {
            sampleAb.addInternship(sampleInternship);
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
