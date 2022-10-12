package seedu.intrack.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.intrack.model.InTrack;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.internship.Address;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InTrack} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new Name("Alex Yeoh"), new Position("Software Developer"),
                new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Status("Progressing"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Internship(new Name("Bernice Yu"), new Position("Data Analyst"),
                new Phone("99272758"), new Email("berniceyu@example.com"),
                new Status("Progressing"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Internship(new Name("Charlotte Oliveiro"), new Position("Machine Learning Expert"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                new Status("Progressing"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Internship(new Name("David Li"), new Position("Frontend Developer"),
                new Phone("91031282"), new Email("lidavid@example.com"),
                new Status("Progressing"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Internship(new Name("Irfan Ibrahim"), new Position("Backend Developer"),
                new Phone("92492021"), new Email("irfan@example.com"),
                new Status("Progressing"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Internship(new Name("Roy Balakrishnan"), new Position("Full Stack Developer"),
                new Phone("92624417"), new Email("royb@example.com"),
                new Status("Progressing"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyInTrack getSampleInTrack() {
        InTrack sampleIt = new InTrack();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleIt.addInternship(sampleInternship);
        }
        return sampleIt;
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
