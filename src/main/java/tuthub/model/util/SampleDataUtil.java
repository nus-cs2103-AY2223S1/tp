package tuthub.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import tuthub.model.ReadOnlyTuthub;
import tuthub.model.Tuthub;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.CommentList;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.Rating;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.TeachingNomination;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;

/**
 * Contains utility methods for populating {@code Tuthub} with sample data.
 */
public class SampleDataUtil {
    public static Tutor[] getSampleTutors() {
        return new Tutor[] {
            new Tutor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("e1234567@u.nus.edu"),
                getModuleSet("cs2103"), new Year("4"), new StudentId("A0234567Y"), new CommentList(),
                new TeachingNomination("0"), new Rating("0.0"),
                getTagSet("lowRatings")),
            new Tutor(new Name("Bernice Yu"), new Phone("99272758"), new Email("e1234517@u.nus.edu"),
                getModuleSet("cs2100", "cs2105"), new Year("3"), new StudentId("A0234527X"), new CommentList(),
                new TeachingNomination("4"), new Rating("2.8"),
                getTagSet("highNominations")),
            new Tutor(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("e1234569"),
                getModuleSet("cs2105"), new Year("3"), new StudentId("A0234127M"), new CommentList(),
                new TeachingNomination("1"), new Rating("3.2"),
                getTagSet("decent")),
            new Tutor(new Name("David Li"), new Phone("91031282"), new Email("e2353621@u.nus.edu"),
                getModuleSet("cs2100", "cs2103t"), new Year("3"), new StudentId("A1674832L"), new CommentList(),
                new TeachingNomination("0"), new Rating("1.1"),
                getTagSet("lowRatings")),
            new Tutor(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("e5216245@u.nus.edu"),
                getModuleSet("cs2103"), new Year("4"), new StudentId("A9876542R"), new CommentList(),
                new TeachingNomination("2"), new Rating("4.2"),
                getTagSet("highRatings")),
            new Tutor(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("e5637124@u.nus.edu"),
                getModuleSet("cs2102", "cs1101s"), new Year("3"), new StudentId("A3498732U"), new CommentList(),
                new TeachingNomination("0"), new Rating("4.5"),
                getTagSet("highRatings"))
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
     * Returns a module set containing the list of strings given.
     */
    public static Set<Module> getModuleSet(String... strings) {
        return Arrays.stream(strings)
            .map(Module::new)
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
