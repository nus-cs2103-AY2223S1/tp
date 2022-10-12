package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALIBABA = new InternshipBuilder().withCompany("Alibaba")
            .withAppliedDate("30 Oct 2022").withDescription("Software Engineering Intern")
            .withLink("https://careers.alibaba.com/positionDetail")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withTags("Ecommerce").build();
    public static final Internship BINANCE = new InternshipBuilder().withCompany("Binance")
            .withAppliedDate("5 Oct 2022")
            .withDescription("Software Engineering Internship (6 months)")
            .withLink("https://careers.binance.com/positionDetail")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withTags("Crypto").build();
    public static final Internship CITADEL = new InternshipBuilder().withCompany("Citadel")
            .withLink("https://careers.Citadel.com/positionDetail")
            .withDescription("Software Engineer Intern").withAppliedDate("23 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withTags("Backend").build();
    public static final Internship DELL = new InternshipBuilder().withCompany("Dell")
            .withLink("https://careers.Dell.com/positionDetail")
            .withDescription("Software Developer Intern").withAppliedDate("30 Sep 2022")
            .withApplicationStatus(ApplicationStatus.Applied).withTags("Backend").build();
    public static final Internship EBAY = new InternshipBuilder().withCompany("Ebay")
            .withLink("https://careers.Ebay.com/positionDetail")
            .withDescription("Software Engineer Intern 2023").withAppliedDate("27 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withTags("Ecommerce").build();
    public static final Internship FACEBOOK = new InternshipBuilder().withCompany("Facebook")
            .withLink("https://careers.Facebook.com/positionDetail")
            .withDescription("Software Engineer Intern - Mobile (Flutter)").withAppliedDate("3 Nov 2022")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withTags("Frontend").build();
    public static final Internship GOLDMAN = new InternshipBuilder().withCompany("Goldman Sachs")
            .withLink("https://careers.GoldmanSachs.com/positionDetail")
            .withDescription("Software Engineer Program - Summer Analyst").withAppliedDate("21 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied)
            .withTags("Backend").build();

    // Manually added
    public static final Internship HUAWEI = new InternshipBuilder().withCompany("Huawei")
            .withLink("https://careers.Huawei.com/positionDetail")
            .withDescription("").withAppliedDate("15 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship INDEED = new InternshipBuilder().withCompany("Indeed")
            .withLink("https://careers.Indeed.com/positionDetail")
            .withDescription("").withAppliedDate("29 Sep 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship GOOGLE = new InternshipBuilder().withCompany(VALID_COMPANY_GOOGLE)
            .withLink(VALID_LINK_GOOGLE).withDescription(VALID_DESCRIPTION_GOOGLE)
            .withAppliedDate(VALID_APPLIED_DATE_GOOGLE).withTags(VALID_TAG_FRONTEND).build();
    public static final Internship TIKTOK = new InternshipBuilder().withCompany(VALID_COMPANY_TIKTOK)
            .withLink(VALID_LINK_TIKTOK).withDescription(VALID_DESCRIPTION_TIKTOK)
            .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).withTags(VALID_TAG_BACKEND, VALID_TAG_AI)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternships() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Internship internship : getTypicalInternships()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALIBABA, BINANCE, CITADEL, DELL, EBAY, FACEBOOK, GOLDMAN));
    }
}
