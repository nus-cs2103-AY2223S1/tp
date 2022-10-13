package seedu.phu.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.phu.model.InternshipBook;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Date;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.internship.Position;
import seedu.phu.model.internship.Remark;
import seedu.phu.model.internship.Website;
import seedu.phu.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternshipBook} with sample data.
 */
public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new Name("Google"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Remark("Blk 30 Geylang Street 29, #06-40"), new Position("Backend Intern"),
                new ApplicationProcess("Apply"), new Date("23-09-2022"),
                new Website("https://careers.google.com/jobs"), getTagSet("friends")),
            new Internship(new Name("TikTok"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Remark("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Position("Frontend Intern"),
                new ApplicationProcess("offer"), new Date("05-10-2022"),
                new Website("https://tiktok.com/careers"), getTagSet("colleagues", "friends")),
            new Internship(new Name("Amazon"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Remark("Blk 11 Ang Mo Kio Street 74, #11-04"), new Position("Fullstack Intern"),
                new ApplicationProcess("accepted"), new Date("01-08-2022"),
                new Website("https://amazon.com/careers"), getTagSet("neighbours")),
            new Internship(new Name("Grab"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Remark("Blk 436 Serangoon Gardens Street 26, #16-43"), new Position("Data Analyst Intern"),
                new ApplicationProcess("assessment"), new Date("01-11-2022"),
                new Website("https://grab.com/careers"), getTagSet("family")),
            new Internship(new Name("Meta"), new Phone("92492021"), new Email("irfan@example.com"),
                new Remark("Blk 47 Tampines Street 20, #17-35"), new Position("Software Engineer Intern"),
                new ApplicationProcess("rejected"), new Date("10-07-2022"),
                new Website("https://grab.com/careers"), getTagSet("classmates")),
            new Internship(new Name("Shopback"), new Phone("92624417"), new Email("royb@example.com"),
                new Remark("Blk 45 Aljunied Street 85, #11-31"), new Position("AI Intern"),
                new ApplicationProcess("interview"), new Date("10-10-2022"),
                new Website("https://shopback.com/careers"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyInternshipBook getSampleInternshipBook() {
        InternshipBook sampleAb = new InternshipBook();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleAb.addInternship(sampleInternship);
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
