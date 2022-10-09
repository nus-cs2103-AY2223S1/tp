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
public class TypicalPersons {

    public static final Internship ALICE = new InternshipBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withApplicationStatus(ApplicationStatus.Applied)
            .withTags("friends").build();
    public static final Internship BENSON = new InternshipBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withApplicationStatus(ApplicationStatus.Applied)
            .withTags("owesMoney", "friends").build();
    public static final Internship CARL = new InternshipBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship DANIEL = new InternshipBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withApplicationStatus(ApplicationStatus.Applied).withTags("friends").build();
    public static final Internship ELLE = new InternshipBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship FIONA = new InternshipBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship GEORGE = new InternshipBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withApplicationStatus(ApplicationStatus.Applied).build();

    // Manually added
    public static final Internship HOON = new InternshipBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withApplicationStatus(ApplicationStatus.Applied).build();
    public static final Internship IDA = new InternshipBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withApplicationStatus(ApplicationStatus.Applied).build();

    // Manually added - Internship's details found in {@code CommandTestUtil}
    public static final Internship AMY = new InternshipBuilder().withName(VALID_NAME_GOOGLE).withPhone(VALID_PHONE_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE).withAddress(VALID_ADDRESS_GOOGLE).withTags(VALID_TAG_FRIEND).build();
    public static final Internship BOB = new InternshipBuilder().withName(VALID_NAME_TIKTOK).withPhone(VALID_PHONE_TIKTOK)
            .withEmail(VALID_EMAIL_TIKTOK).withAddress(VALID_ADDRESS_TIKTOK).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Internship internship : getTypicalPersons()) {
            ab.addInternship(internship);
        }
        return ab;
    }

    public static List<Internship> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
