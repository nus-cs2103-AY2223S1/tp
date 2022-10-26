package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FindMyIntern;
import seedu.address.model.ReadOnlyFindMyIntern;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */


public class SampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[] {
            new Internship(new Company("Google"), new Link("https://careers.google.com/students"),
                new Description("Software engineering internship, "
                        + "work on projects related to mobile development, distributed and parallel systems"),
                    ApplicationStatus.Interviewed, new AppliedDate("03/09/2022"),
                    new InterviewDateTime("29/09/2022 13:30"), getTagSet("Backend")),
            new Internship(new Company("TikTok"), new Link("https://careers.tiktok.com"),
                new Description("Mobile software engineering internship, "
                        + "design and implement new user features of mobile application"),
                    ApplicationStatus.Rejected, new AppliedDate("11/09/2022"),
                    new InterviewDateTime("23/09/2022 14:00"), getTagSet("Frontend")),
            new Internship(new Company("Stripe"), new Link("https://stripe.com/en-sg/jobs/university"),
                new Description("Software engineering internship, write software for production, "
                        + "requires knowledge on how to handle HTTP requests"), ApplicationStatus.Accepted,
                new AppliedDate("26/09/2022"), new InterviewDateTime("15/10/2022 10:30"), getTagSet("Backend")),
            new Internship(new Company("Meta"), new Link("https://metacareers.com/careerprograms/students"),
                new Description("Software engineering internship, work on assigned codebase, "
                        + "product area, and/or system"), ApplicationStatus.Shortlisted,
                new AppliedDate("05/10/2022"), new InterviewDateTime("23/10/2022 15:00"), getTagSet("Backend")),
            new Internship(new Company("Jane Street"), new Link("https://janestreet.com/join-jane-street"),
                new Description("Software engineering internship, work on two projects, learn OCaml, "
                        + "gain exposure to libraries and tools used in internal systems"),
                    ApplicationStatus.Applied, new AppliedDate("11/10/2022"),
                    null, getTagSet("Quant")),
            new Internship(new Company("Visa"), new Link("https://www.visa.com.sg/careers.html"),
                new Description("Software engineering internship, get executive exposure, "
                        + "engage in out-of-the-box problem solving"), ApplicationStatus.Shortlisted,
                new AppliedDate("23/10/2022"), new InterviewDateTime("15/11/2022 16:15"), getTagSet("Backend"))
        };
    }

    public static ReadOnlyFindMyIntern getSampleFindMyIntern() {
        FindMyIntern sampleFmi = new FindMyIntern();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleFmi.addInternship(sampleInternship);
        }
        return sampleFmi;
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
