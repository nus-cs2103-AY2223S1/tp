package seedu.application.model.util;


import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.*;
import seedu.application.model.application.interview.*;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {
    public static Application[] getSampleApplications() {
        Interview[] interviews = getSampleInterviews();

        return new Application[] {
            new Application(new Company("Google"), new Contact("68882888"), new Email("internships@google.com.sg"),
                    new Position("Software Engineer Intern"), new Date("2022-09-10"), interviews[0]),
            new Application(new Company("Grab"), new Contact("67773777"), new Email("internships@grab.com.sg"),
                    new Position("Backend Engineer Intern"), new Date("2022-10-01"), interviews[1]),
            new Application(new Company("ByteDance"), new Contact("63334333"), new Email("hr@bytedance.com.sg"),
                    new Position("Frontend Engineer Intern"), new Date("2021-08-08"), interviews[2]),
            new Application(new Company("Shopee"), new Contact("67436743"), new Email("talent@shopee.com.sg"),
                    new Position("Quality Assurance Intern"), new Date("2021-09-30"), interviews[3]),
            new Application(new Company("Wise"), new Contact("64164161"), new Email("humanresource@wise.org"),
                    new Position("Software Engineer Intern"), new Date("2022-09-10"), interviews[4]),
            new Application(new Company("Stripe"), new Contact("66210987"), new Email("intern@stripe.org"),
                    new Position("System Analyst"), new Date("2022-10-08"), interviews[5])
        };
    }

    private static Interview[] getSampleInterviews() {
        return new Interview[] {
                new Interview(new Round("Technical interview round 1"), new InterviewDate("2022-11-15"),
                        new InterviewTime("1000"), new Location("Zoom")),
                new Interview(new Round("Technical interview round 2"), new InterviewDate("2022-11-30"),
                        new InterviewTime("0900"), new Location("Microsoft Teams")),
                new Interview(new Round("Technical interview round 3"), new InterviewDate("2022-12-08"),
                        new InterviewTime("1130"), new Location("77, Kallang Way 7, #02-18")),
                new Interview(new Round("HR interview"), new InterviewDate("2023-01-31"),
                        new InterviewTime("1345"), new Location("11, Science Park 2, #01-04")),
                new Interview(new Round("Manager interview"), new InterviewDate("2023-02-12"),
                        new InterviewTime("1500"), new Location("69, Keppel Tech Park, #04-20")),
                new Interview(new Round("Online assessment"), new InterviewDate("2023-01-02"),
                        new InterviewTime("1630"), new Location("Online"))
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
