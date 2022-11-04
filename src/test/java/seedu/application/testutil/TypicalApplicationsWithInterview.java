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
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_PREFERRED;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_TECH_COMPANY;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_BYTEDANCE;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_FACEBOOK;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_GOOGLE;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_JANE_STREET;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_SHOPEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.model.ApplicationBook;
import seedu.application.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects with {@code Interview} to be used in tests.
 */
public class TypicalApplicationsWithInterview {

    public static final Application SHOPEE = new ApplicationBuilder().withCompany("Shopee")
            .withContact("94201239").withDate("2022-10-30")
            .withEmail("shopee@example.com").withPosition("Frontend Engineer").withTags("consumerTech")
            .withInterview(INTERVIEW_SHOPEE).build();
    public static final Application BYTEDANCE = new ApplicationBuilder().withCompany("ByteDance")
            .withContact("83920382").withDate("2022-08-01")
            .withEmail("bytedance@example.com").withPosition("Backend Engineer").withTags("BytePlus", "TikTok")
            .withInterview(INTERVIEW_BYTEDANCE).build();

    // Manually added
    public static final Application JANE_STREET = new ApplicationBuilder().withCompany("Jane Street")
            .withContact("91420834").withDate("2022-01-01")
            .withEmail("janestreet@example.com").withPosition("Software Engineer").withTags("fintech")
            .withInterview(INTERVIEW_JANE_STREET).build();

    // Manually added - Application and Interview's details found in {@code CommandTestUtil} and
    // {@code TypicalInterviews}.
    public static final Application GOOGLE = new ApplicationBuilder().withCompany(VALID_COMPANY_GOOGLE)
            .withContact(VALID_CONTACT_GOOGLE).withDate(VALID_DATE_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE).withPosition(VALID_POSITION_GOOGLE).withTags(VALID_TAG_TECH_COMPANY)
            .withInterview(INTERVIEW_GOOGLE).build();

    public static final Application FACEBOOK = new ApplicationBuilder().withCompany(VALID_COMPANY_FACEBOOK)
            .withContact(VALID_CONTACT_FACEBOOK).withDate(VALID_DATE_FACEBOOK)
            .withEmail(VALID_EMAIL_FACEBOOK).withPosition(VALID_POSITION_FACEBOOK)
            .withTags(VALID_TAG_PREFERRED, VALID_TAG_TECH_COMPANY)
            .withInterview(INTERVIEW_FACEBOOK).build();

    private TypicalApplicationsWithInterview() {} // prevents instantiation

    /**
     * Returns an {@code ApplicationBook} with all the typical applications with interviews.
     */
    public static ApplicationBook getTypicalApplicationBookWithInterview() {
        ApplicationBook ab = new ApplicationBook();
        for (Application application : getTypicalApplicationsWithInterview()) {
            ab.addApplication(application);
        }
        return ab;
    }

    public static List<Application> getTypicalApplicationsWithInterview() {
        return new ArrayList<>(Arrays.asList(SHOPEE, BYTEDANCE, JANE_STREET));
    }
}
