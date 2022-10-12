package tuthub.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import tuthub.model.Tuthub;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.tutor.Address;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Year;
import tuthub.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Tuthub} with sample data.
 */
public class SampleDataUtil {
    public static Tutor[] getSampleTutors() {
        return new Tutor[] {
            new Tutor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Module("cs2103"), new Year("4"), new StudentId("A0234567Y"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Comment(""),
                getTagSet("friends")),
            new Tutor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Module("cs2100"), new Year("3"), new StudentId("A0234527X"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Comment(""),
                getTagSet("colleagues", "friends")),
            new Tutor(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Module("cs2105"), new Year("3"), new StudentId("A0234127M"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Comment(""),
                getTagSet("neighbours")),
            new Tutor(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Module("cs2100"), new Year("3"), new StudentId("A1674832L"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Comment(""),
                getTagSet("family")),
            new Tutor(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Module("cs2103"), new Year("4"), new StudentId("A9876542R"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Comment(""),
                getTagSet("classmates")),
            new Tutor(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Module("cs2102"), new Year("3"), new StudentId("A3498732U"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Comment(""),
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
