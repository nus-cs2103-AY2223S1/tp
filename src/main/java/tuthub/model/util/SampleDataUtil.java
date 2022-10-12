package tuthub.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import tuthub.model.ReadOnlyTuthub;
import tuthub.model.Tuthub;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;

/**
 * Contains utility methods for populating {@code Tuthub} with sample data.
 */
public class SampleDataUtil {
    public static Tutor[] getSampleTutors() {
        return new Tutor[] {
            new Tutor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Module("cs2103"), new Year("4"), new StudentId("A0234567Y"), new Comment(""),
                getTagSet("friends")),
            new Tutor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Module("cs2100"), new Year("3"), new StudentId("A0234527X"), new Comment(""),
                getTagSet("colleagues", "friends")),
            new Tutor(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Module("cs2105"), new Year("3"), new StudentId("A0234127M"), new Comment(""),
                getTagSet("neighbours")),
            new Tutor(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Module("cs2100"), new Year("3"), new StudentId("A1674832L"), new Comment(""),
                getTagSet("family")),
            new Tutor(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Module("cs2103"), new Year("4"), new StudentId("A9876542R"), new Comment(""),
                getTagSet("classmates")),
            new Tutor(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Module("cs2102"), new Year("3"), new StudentId("A3498732U"), new Comment(""),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyTuthub getSampleTuthub() {
        Tuthub sampleAb = new Tuthub();
        for (Tutor sampleTutor : getSampleTutors()) {
            sampleAb.addTutor(sampleTutor);
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
