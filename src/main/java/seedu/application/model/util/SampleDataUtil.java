package seedu.application.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] {
            new Application(new Company("Google"), new Contact("68882888"), new Email("internships@google.com.sg"),
                    new Position("Software Engineer Intern"), new Date("2022-09-10"),
                    getTagSet("preferred")),
            new Application(new Company("Grab"), new Contact("67773777"), new Email("internships@grab.com.sg"),
                    new Position("Backend Engineer Intern"), new Date("2022-10-01"),
                    getTagSet("senior referral")),
            new Application(new Company("ByteDance"), new Contact("63334333"), new Email("hr@bytedance.com.sg"),
                    new Position("Frontend Engineer Intern"), new Date("2021-08-08"),
                    getTagSet("BytePlus")),
            new Application(new Company("Shopee"), new Contact("67436743"), new Email("talent@shopee.com.sg"),
                    new Position("Quality Assurance Intern"), new Date("2021-09-30"),
                    getTagSet("consumer tech")),
            new Application(new Company("Wise"), new Contact("64164161"), new Email("humanresource@wise.org"),
                    new Position("Software Engineer Intern"), new Date("2022-09-10"),
                    getTagSet("fintech company")),
            new Application(new Company("Stripe"), new Contact("66210987"), new Email("intern@stripe.org"),
                    new Position("System Analyst"), new Date("2022-10-08"),
                    getTagSet("financial services", "tech"))
        };
    }

    public static ReadOnlyApplicationBook getSampleApplicationBook() {
        ApplicationBook sampleAb = new ApplicationBook();
        for (Application sampleApplication : getSampleApplications()) {
            sampleAb.addApplication(sampleApplication);
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
