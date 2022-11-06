package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.nextofkin.Relationship;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("mischievous"), new School("Geylang Primary School"), Level.PRIMARY5,
                    new NextOfKin(new Name("Mary Yeoh"), new Phone("91023376"), new Email("maryyeoh@example.com"),
                            new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friendly"),
                            Relationship.MOTHER)),
            new Tutor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("teachAmath", "teachEmath"), new Qualification("Bachelor of Maths"),
                    new Institution("National University of Singapore")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("weakInMaths"), new School("Ang Mo Kio Secondary School"), Level.SECONDARY3,
                    new NextOfKin(new Name("Adam Oliveiro"), new Phone("83245908"), new Email("brendan@example.com"),
                            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("helpful"),
                            Relationship.FATHER)),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("weakInEnglish"), new School("Serangoon Primary School"), Level.PRIMARY5,
                    new NextOfKin(new Name("Dylan Li"), new Phone("85467812"), new Email("lidylan@example.com"),
                            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                            getTagSet("formerStudent"), Relationship.BROTHER)),
            new Tutor(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("teachEnglish"),
                    new Qualification("Bachelor of Arts"), new Institution("Nanyang Technological University")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("weakInMaths"),
                    new School("Aljunied Secondary School"), Level.SECONDARY2,
                    new NextOfKin(new Name("Mary Balakrishnan"), new Phone("90054378"), new Email("mary@example.com"),
                            new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("friendly"),
                            Relationship.MOTHER))
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
