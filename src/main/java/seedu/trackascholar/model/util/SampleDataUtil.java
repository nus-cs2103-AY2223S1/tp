package seedu.trackascholar.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TrackAScholar} with sample data.
 */
public class SampleDataUtil {
    public static Applicant[] getSamplePersons() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Scholarship("NUS Global Merit Scholarship"), new ApplicationStatus("pending"),
                getTagSet("friends")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Scholarship("NUS Merit Scholarship"),
                new ApplicationStatus("pending"), getTagSet("colleagues", "friends")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Scholarship("NUS Sports Scholarship"),
                new ApplicationStatus("accepted"), getTagSet("neighbours")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Scholarship("NUS Performing & Visual Arts Scholarship"),
                new ApplicationStatus("rejected"), getTagSet("family")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Scholarship("NUS Merit Scholarship"), new ApplicationStatus("pending"),
                getTagSet("classmates")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Scholarship("NUS Performing & Visual Arts Scholarship"), new ApplicationStatus("rejected"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTrackAScholar getSampleTrackAScholar() {
        TrackAScholar sampleTrackAScholar = new TrackAScholar();
        for (Applicant sampleApplicant : getSamplePersons()) {
            sampleTrackAScholar.addApplicant(sampleApplicant);
        }
        return sampleTrackAScholar;
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
