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
            new Internship(new Name("Microsoft"), new Position("Frontend Developer"),
                new Phone("22222222"), new Email("hr@microsoft.com"),
                new Status("Progress"), new Address("Microsoft Campus"),
                getTagSet("Urgent")),
            new Internship(new Name("Apple"), new Position("Software Engineer"),
                new Phone("11111111"), new Email("hr@apple.com"),
                new Status("Offered"), new Address("Apple Park"),
                getTagSet("Remote")),
            new Internship(new Name("Tesla"), new Position("Machine Learning Expert"),
                new Phone("63456789"), new Email("hr@tesla.com"),
                new Status("Rejected"), new Address("Tesla Gigafactory"),
                getTagSet("Something")),
            new Internship(new Name("Alphabet"), new Position("Full Stack Developer"),
                new Phone("64567890"), new Email("hr@alphabet.com"),
                new Status("Progress"), new Address("Googleplex"),
                getTagSet("Urgent")),
            new Internship(new Name("Nvidia"), new Position("Data Analyst"),
                new Phone("65678901"), new Email("hr@nvidia.com"),
                new Status("Offered"), new Address("Nvidia HQ"),
                getTagSet("Remote")),
            new Internship(new Name("Amazon"), new Position("Backend Developer"),
                new Phone("66789012"), new Email("hr@amazon.com"),
                new Status("Rejected"), new Address("Amazon HQ"),
                getTagSet("Something"))
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
