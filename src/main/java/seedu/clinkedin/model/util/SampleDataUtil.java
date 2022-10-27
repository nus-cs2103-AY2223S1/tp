package seedu.clinkedin.model.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.ReadOnlyAddressBook;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.Status;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Note EMPTY_NOTE = new Note("");
    public static final Set<Link> EMPTY_LINKS = new HashSet<>();


    public static Person[] getSamplePersons() throws MalformedURLException {
        //        return null;
        //    to be rewritten.
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagTypeMap("friends"),
                    new Status("Application Received"), new Note("Has a dog."), new Rating("9"),
                    getLinkSet(generateUrls("https://instagram.com", "https://github.com"))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagTypeMap("colleagues", "friends"),
                    new Status("Application Received"), EMPTY_NOTE, new Rating("5"), EMPTY_LINKS),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagTypeMap("neighbours"), new Status("OA in Progress"), EMPTY_NOTE,
                    new Rating("7"), getLinkSet(generateUrls("https://google.com"))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagTypeMap("family"), new Status("Application Withdrawn"), EMPTY_NOTE,
                    new Rating("6"), getLinkSet(generateUrls("https://telegram.org"))),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagTypeMap("classmates"), new Status("Rejected"),
                    new Note("Proficient in Python."), new Rating("3"), EMPTY_LINKS),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagTypeMap("colleagues"), new Status("Interview in Progress"), EMPTY_NOTE,
                    new Rating("3"), getLinkSet(generateUrls("https://snapchat.com")))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() throws MalformedURLException {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static UniqueTagTypeMap getTagTypeMap(String... strings) {
        UniqueTagTypeMap tags = new UniqueTagTypeMap();
        for (String tag: strings) {
            tags.mergeTag(new TagType("Skills", new Prefix("st/")), new Tag(tag));
        }
        return tags;
    }

    /**
     * Returns a set of links objects instantiated with the provided URLs.
     * @param strings URLs needed to instantiate Set of Links.
     * @return Set of links.
     * @throws MalformedURLException
     */
    public static URL[] generateUrls(String... strings) throws MalformedURLException {
        try {
            URL[] urls = new URL[strings.length];
            int i = 0;
            for (String url: strings) {
                urls[i] = new URL(url);
                i++;
            }
            return urls;
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
        }
    }

    /**
     * Returns a link set containing the list of strings given.
     */
    public static Set<Link> getLinkSet(URL... urls) {
        return Arrays.stream(urls)
                    .map(Link::new)
                    .collect(Collectors.toSet());
    }
}
