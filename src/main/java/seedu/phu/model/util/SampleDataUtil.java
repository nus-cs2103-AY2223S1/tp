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
                new Remark("Technical Interview (3 Questions - 2 Medium, 1 Hard)"),
                new Position("Backend Intern"), new ApplicationProcess("Apply"), new Date("20-10-2022"),
                new Website("https://careers.google.com/"), getTagSet("Golang", "Summer")),
            new Internship(new Name("TikTok"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Remark("One Raffles Quay, Level 26 South Tower, Singapore 048583"), new Position("Frontend Intern"),
                new ApplicationProcess("offer"), new Date("25-10-2022"),
                new Website("https://careers.tiktok.com/"), getTagSet("ATAP","Summer")),
            new Internship(new Name("Amazon"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Remark("Behavioural Interview"), new Position("Fullstack Intern"),
                new ApplicationProcess("assessment"), new Date("27-10-2022"),
                new Website("https://amazon.com/careers"), getTagSet()),
            new Internship(new Name("Grab"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Remark("NA"), new Position("Data Analyst Intern"),
                new ApplicationProcess("assessment"), new Date("28-10-2022"),
                new Website("https://grab.careers/"), getTagSet("ATAP")),
            new Internship(new Name("Meta"), new Phone("92492021"), new Email("alice@example.com"),
                new Remark("NA"), new Position("Software Engineer Intern"),
                new ApplicationProcess("rejected"), new Date("10-10-2022"),
                new Website("https://www.metacareers.com/"), getTagSet()),
            new Internship(new Name("Shopback"), new Phone("92624417"), new Email("royb@example.com"),
                new Remark("Final Round - 1 Behavioural and 2 Technical"), new Position("AI Intern"),
                new ApplicationProcess("interview"), new Date("25-10-2022"),
                new Website("https://corporate.shopback.com/careers"), getTagSet("Winter"))
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
