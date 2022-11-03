package seedu.application.testutil;

import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_CONTACT_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DATE_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_EMAIL_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_POSITION_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_PREFERRED;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_TECH_COMPANY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.model.ApplicationBook;
import seedu.application.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalApplications {

    public static final Application SHOPEE = new ApplicationBuilder().withCompany("Shopee")
            .withContact("94201239").withDate("2022-12-31").withStatus("interview")
            .withEmail("shopee@example.com").withPosition("Frontend Engineer").withTags("consumerTech").build();

    public static final Application BYTEDANCE = new ApplicationBuilder().withCompany("ByteDance")
            .withContact("83920382").withDate("2022-08-01").withStatus("offered")
            .withEmail("bytedance@example.com").withPosition("Backend Engineer").withTags("BytePlus", "TikTok").build();

    // Manually added
    public static final Application JANE_STREET = new ApplicationBuilder().withCompany("Jane Street")
            .withContact("91420834").withDate("2022-01-01").withStatus("pending")
            .withEmail("janestreet@example.com").withPosition("Software Engineer").withTags("fintech").build();

    public static final Application INTEL = new ApplicationBuilder().withCompany("Intel")
            .withContact("84893063").withDate("2022-05-06").withEmail("intel@recruitment.com")
            .withPosition("Software Developer").withStatus("rejected")
            .withArchiveStatus(true).withStatus("pending").build();

    public static final Application WISE = new ApplicationBuilder().withCompany("Wise")
            .withContact("88228822").withDate("2022-08-28")
            .withEmail("wise@example.com").withPosition("Software Engineer").withTags("financialService")
            .withInterview(new InterviewBuilder().withInterviewDate(LocalDate.now().plusDays(1)).build())
            .build();

    public static final Application STRIPE = new ApplicationBuilder().withCompany("Stripe")
            .withContact("87879898").withDate("2022-01-02")
            .withEmail("stripe@example.com").withPosition("Software Engineer").withTags("financialService")
            .withInterview(new InterviewBuilder().withInterviewDate(LocalDate.now().minusDays(1)).build())
            .build();

    public static final Application GOVTECH = new ApplicationBuilder().withCompany("GovTech")
            .withContact("65556444").withDate("2022-05-06")
            .withEmail("govtech@gov.sg").withPosition("Software Engineer").withTags("publicSector")
            .withInterview(new InterviewBuilder().withInterviewDate(LocalDate.now().plusDays(8)).build())
            .build();

    // Manually added - Application's details found in {@code CommandTestUtil}
    public static final Application GOOGLE = new ApplicationBuilder().withCompany(VALID_COMPANY_GOOGLE)
            .withContact(VALID_CONTACT_GOOGLE).withDate(VALID_DATE_GOOGLE).withStatus(VALID_STATUS_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE).withPosition(VALID_POSITION_GOOGLE).withTags(VALID_TAG_TECH_COMPANY).build();

    public static final Application FACEBOOK = new ApplicationBuilder().withCompany(VALID_COMPANY_FACEBOOK)
            .withContact(VALID_CONTACT_FACEBOOK).withDate(VALID_DATE_FACEBOOK)
            .withEmail(VALID_EMAIL_FACEBOOK).withPosition(VALID_POSITION_FACEBOOK)
            .withStatus(VALID_STATUS_FACEBOOK).withTags(VALID_TAG_PREFERRED, VALID_TAG_TECH_COMPANY).build();

    public static final String KEYWORD_MATCHING_GOOGLE = "Google"; // A keyword that matches GOOGLE

    private TypicalApplications() {} // prevents instantiation

    /**
     * Returns an {@code ApplicationBook} with all the typical applications.
     */
    public static ApplicationBook getTypicalApplicationBook() {
        ApplicationBook ab = new ApplicationBook();
        for (Application application : getTypicalApplications()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static ApplicationBook getTypicalApplicationBookWithNoUpcomingInterview() {
        ApplicationBook ab = new ApplicationBook();
        for (Application application : getTypicalApplicationsWithNoUpcomingInterview()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static ApplicationBook getTypicalApplicationBookWithUpcomingInterview() {
        ApplicationBook ab = new ApplicationBook();
        for (Application application : getTypicalApplicationsWithUpcomingInterview()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static ApplicationBook getTypicalApplicationBookWithArchive() {
        ApplicationBook ab = new ApplicationBook();
        for (Application application : getTypicalApplicationsWithArchive()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static ApplicationBook getApplicationBookWithOneApplication() {
        ApplicationBook ab = new ApplicationBook();
        ab.addApplication(INTEL);
        return ab;
    }

    public static List<Application> getTypicalApplications() {
        return new ArrayList<>(Arrays.asList(SHOPEE, BYTEDANCE, JANE_STREET));
    }

    public static List<Application> getTypicalApplicationsWithNoUpcomingInterview() {
        return new ArrayList<>(Arrays.asList(STRIPE, GOVTECH));
    }

    public static List<Application> getTypicalApplicationsWithUpcomingInterview() {
        return new ArrayList<>(Arrays.asList(SHOPEE, BYTEDANCE, JANE_STREET, WISE));
    }

    public static List<Application> getTypicalApplicationsWithArchive() {
        return new ArrayList<>(Arrays.asList(SHOPEE, BYTEDANCE, JANE_STREET, INTEL));
    }
}
