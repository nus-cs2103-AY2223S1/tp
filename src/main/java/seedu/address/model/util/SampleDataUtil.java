package seedu.address.model.util;

import seedu.address.model.ApplicationBook;
import seedu.address.model.ReadOnlyApplicationBook;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Contact;
import seedu.address.model.application.Date;
import seedu.address.model.application.Email;
import seedu.address.model.application.Position;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        return new Application[] {
            new Application(new Company("Google"), new Contact("68882888"), new Email("internships@google.com.sg"),
                    new Position("Software Engineer Intern"), new Date("2022-09-10")),
            new Application(new Company("Grab"), new Contact("67773777"), new Email("internships@grab.com.sg"),
                    new Position("Backend Engineer Intern"), new Date("2022-10-01")),
            new Application(new Company("ByteDance"), new Contact("63334333"), new Email("hr@bytedance.com.sg"),
                    new Position("Frontend Engineer Intern"), new Date("2021-08-08")),
            new Application(new Company("Shopee"), new Contact("67436743"), new Email("talent@shopee.com.sg"),
                    new Position("Quality Assurance Intern"), new Date("2021-09-30")),
            new Application(new Company("Wise"), new Contact("64164161"), new Email("humanresource@wise.org"),
                    new Position("Software Engineer Intern"), new Date("2022-09-10")),
            new Application(new Company("Stripe"), new Contact("66210987"), new Email("intern@stripe.org"),
                    new Position("System Analyst"), new Date("2022-10-08"))
        };
    }

    public static ReadOnlyApplicationBook getSampleApplicationBook() {
        ApplicationBook sampleAb = new ApplicationBook();
        for (Application sampleApplication : getSampleApplications()) {
            sampleAb.addApplication(sampleApplication);
        }
        return sampleAb;
    }

}
