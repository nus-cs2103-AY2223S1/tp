package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ApplicationBook;
import seedu.address.model.application.Application;

/**
 * A utility class containing a list of {@code Application} objects to be used in tests.
 */
public class TypicalApplications {

    public static final Application SHOPEE = new ApplicationBuilder().withCompany("Shopee")
            .withContact("94201239").withDate("2022-12-31")
            .withEmail("shopee@example.com").withPosition("Frontend Engineer").build();
    public static final Application BYTEDANCE = new ApplicationBuilder().withCompany("ByteDance")
            .withContact("83920382").withDate("2022-08-01")
            .withEmail("bytedance@example.com").withPosition("Backend Engineer").build();

    // Manually added
    public static final Application JANE_STREET = new ApplicationBuilder().withCompany("Jane Street")
            .withContact("91420834").withDate("2022-01-01")
            .withEmail("janestreet@example.com").withPosition("Software Engineer").build();

    // Manually added - Application's details found in {@code CommandTestUtil}
    public static final Application GOOGLE = new ApplicationBuilder().withCompany(VALID_COMPANY_GOOGLE)
            .withContact(VALID_CONTACT_GOOGLE).withDate(VALID_DATE_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE).withPosition(VALID_POSITION_GOOGLE).build();
    public static final Application FACEBOOK = new ApplicationBuilder().withCompany(VALID_COMPANY_FACEBOOK)
            .withContact(VALID_CONTACT_FACEBOOK).withDate(VALID_DATE_FACEBOOK)
            .withEmail(VALID_EMAIL_FACEBOOK).withPosition(VALID_POSITION_FACEBOOK).build();

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

    public static List<Application> getTypicalApplications() {
        return new ArrayList<>(Arrays.asList(SHOPEE, BYTEDANCE, JANE_STREET));
    }
}
