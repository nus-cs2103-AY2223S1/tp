package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

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

    public static final Internship ALIBABA = new InternshipBuilder().withName("Alibaba")
            .withAddress("30 Oct 2022").withEmail("careers@alibaba.com")
            .withPhone("94351253").withApplicationStatus(ApplicationStatus.Applied)
            .withTags("friends").build();
    public static final Internship BINANCE = new InternshipBuilder().withName("Binance")
            .withAddress("5 Oct 2022")
            .withEmail("careers@binance.com").withPhone("98765432").withApplicationStatus(ApplicationStatus.Applied)
            .withTags("owesMoney", "friends").build();
    public static final Internship CITADEL = new InternshipBuilder().withName("Citadel").withPhone("95352563")
            .withEmail("careers@citadel.com").withAddress("23 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship DELL = new InternshipBuilder().withName("Dell").withPhone("87652533")
            .withEmail("careers@dell.com").withAddress("30 Sep 2022")
            .withApplicationStatus(ApplicationStatus.Applied).withTags("friends").build();
    public static final Internship EBAY = new InternshipBuilder().withName("Ebay").withPhone("9482224")
            .withEmail("careers@ebay.com").withAddress("27 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship FACEBOOK = new InternshipBuilder().withName("Facebook").withPhone("9482427")
            .withEmail("careers@facebook.com").withAddress("3 Nov 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship GOLDMAN = new InternshipBuilder().withName("Goldman Sachs").withPhone("9482442")
            .withEmail("careers@goldmansachs.com").withAddress("21 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();

    // Manually added
    public static final Internship HUAWEI = new InternshipBuilder().withName("Huawei").withPhone("8482424")
            .withEmail("careers@huawei.com").withAddress("15 Oct 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship INDEED = new InternshipBuilder().withName("Indeed").withPhone("8482131")
            .withEmail("careers@indeed.com").withAddress("29 Sep 2022")
            .withApplicationStatus(ApplicationStatus.Applied).build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship GOOGLE = new InternshipBuilder().withName(VALID_NAME_GOOGLE)
            .withPhone(VALID_PHONE_GOOGLE).withEmail(VALID_EMAIL_GOOGLE)
            .withAddress(VALID_ADDRESS_GOOGLE).withTags(VALID_TAG_FRIEND).build();
    public static final Internship TIKTOK = new InternshipBuilder().withName(VALID_NAME_TIKTOK)
            .withPhone(VALID_PHONE_TIKTOK).withEmail(VALID_EMAIL_TIKTOK)
            .withAddress(VALID_ADDRESS_TIKTOK).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
