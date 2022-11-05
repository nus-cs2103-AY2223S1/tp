package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlySurvin;
import seedu.address.model.Survin;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Survin} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Gender("male"),
                new Birthdate("1989-10-12"), new Race("Chinese"), new Religion("Buddhist"),
                getSurveySet("Environment Survey", "Changi Airport Survey"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Gender("female"),
                new Birthdate("1995-01-28"), new Race("Chinese"), new Religion("Christian"),
                getSurveySet("Changi Airport Survey", "Environment Survey"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Gender("female"),
                new Birthdate("2001-06-06"), new Race("Italian"), new Religion("Christian"),
                getSurveySet("Environment Survey", "Academic Survey"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Gender("male"),
                new Birthdate("1978-04-25"), new Race("Chinese"), new Religion("Free Thinker"),
                getSurveySet("Academic Survey"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Gender("male"),
                new Birthdate("1991-08-21"), new Race("Malay"), new Religion("Muslim"),
                getSurveySet("Academic Survey"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Gender("male"),
                new Birthdate("1995-04-01"), new Race("Indian"), new Religion("Hinduism"),
                getSurveySet("Changi Airport Survey"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlySurvin getSampleSurvin() {
        Survin sampleAb = new Survin();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a survey set containing the list of strings given.
     */
    public static Set<Survey> getSurveySet(String... strings) {
        return Arrays.stream(strings)
                .map(Survey::new)
                .collect(Collectors.toSet());
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
