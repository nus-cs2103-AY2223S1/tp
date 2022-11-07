package seedu.clinkedin.model.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.clinkedin.commons.exceptions.DataConversionException;
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

    public static final UniqueTagTypeMap EMPTY_TAGS = new UniqueTagTypeMap();
    public static final Set<Link> EMPTY_LINKS = new HashSet<>();


    public static Person[] getSamplePersons() throws DataConversionException {
        //        return null;
        //    to be rewritten.
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getCombinedTagTypeMap(
                            getIndividualTagTypeMap("Skills", "st/", "Data Analytics", "R"),
                    getIndividualTagTypeMap("Degree", "dt/", "Bachelors"),
                    getIndividualTagTypeMap("Job Type", "jtt/", "Part Time")),
                    new Status("Application Received"), new Note("Has been in the Dean's List twice!"),
                    new Rating("8"),
                    getLinkSet(generateUrls("https://telegram.org", "https://github.com"))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_TAGS,
                    new Status("Rejected"),
                    new Note("Has experience in marketing."),
                    new Rating("3"), getLinkSet(generateUrls("https://instagram.org"))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getCombinedTagTypeMap(getIndividualTagTypeMap("Skills", "st/",
                            "Machine Learning", "Python"), getIndividualTagTypeMap("Degree",
                            "dt/", "Masters"), getIndividualTagTypeMap("Job Type",
                            "jtt/", "Full Time")), new Status("OA in Progress"),
                    new Note("Has research experience in ML."),
                    new Rating("8"), getLinkSet(generateUrls("https://google.com", "https://twitter.com"))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getCombinedTagTypeMap(getIndividualTagTypeMap("Degree",
                            "dt/", "Bachelors"), getIndividualTagTypeMap("Job Type",
                            "jtt/", "Internship")), new Status("Application Withdrawn"),
                    new Note("Has only worked on individual projects. No job experience."),
                    new Rating("4"), EMPTY_LINKS),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getCombinedTagTypeMap(getIndividualTagTypeMap("Skills", "st/",
                            "Web Development"), getIndividualTagTypeMap("Degree",
                            "dt/", "Bachelors"), getIndividualTagTypeMap("Job Type",
                            "jtt/", "Internship")), new Status("Rejected"),
                    new Note("Proficient in designing."), new Rating("6"),
                    getLinkSet(generateUrls("https://telegram.org"))),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getCombinedTagTypeMap(getIndividualTagTypeMap("Skills", "st/",
                            "PostgreSQL", "ReactJS"), getIndividualTagTypeMap("Degree",
                            "dt/", "Bachelors"), getIndividualTagTypeMap("Job Type",
                            "jtt/", "Part Time")), new Status("Interview in Progress"),
                    new Note("Has 5 years of work experience as a Backend developer!"),
                    new Rating("9"), getLinkSet(generateUrls("https://github.com",
                    "https://google.com", "https://telegram.com")))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() throws DataConversionException {
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
     * Returns a tag set containing the list of strings given.
     */
    public static UniqueTagTypeMap getCombinedTagTypeMap(UniqueTagTypeMap... tagTypeMaps) {
        UniqueTagTypeMap tags = new UniqueTagTypeMap();
        for (UniqueTagTypeMap tagTypeMap: tagTypeMaps) {
            tags.mergeTagTypeMap(tagTypeMap);
        }
        return tags;
    }

    public static UniqueTagTypeMap getIndividualTagTypeMap(String tagType, String prefix, String... strings) {
        UniqueTagTypeMap tags = new UniqueTagTypeMap();
        for (String tag: strings) {
            tags.mergeTag(new TagType(tagType, new Prefix(prefix)), new Tag(tag));
        }
        return tags;
    }

    /**
     * Returns a set of links objects instantiated with the provided URLs.
     * @param strings URLs needed to instantiate Set of Links.
     * @return Set of links.
     * @throws DataConversionException
     */
    public static URL[] generateUrls(String... strings) throws DataConversionException {
        try {
            URL[] urls = new URL[strings.length];
            int i = 0;
            for (String url: strings) {
                urls[i] = new URL(url);
                i++;
            }
            return urls;
        } catch (MalformedURLException mue) {
            throw new DataConversionException(mue);
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
