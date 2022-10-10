package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATION_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATION_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOLARSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TrackAScholar;
import seedu.address.model.applicant.Applicant;

/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Applicant ALICE = new PersonBuilder().withName("Alice Pauline")
            .withPhone("94351253").withEmail("alice@example.com")
            .withScholarship("Global Merit").withApplicationStatus("pending")
            .withTags("friends").build();
    public static final Applicant BENSON = new PersonBuilder().withName("Benson Meier")
            .withPhone("98765432").withEmail("johnd@example.com")
            .withScholarship("Merit").withApplicationStatus("accepted")
            .withTags("owesMoney", "friends").build();
    public static final Applicant CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563").withEmail("heinz@example.com")
            .withScholarship("Sports").withApplicationStatus("pending").build();
    public static final Applicant DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withScholarship("Arts").withApplicationStatus("rejected").withTags("friends").build();
    public static final Applicant ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224").withEmail("werner@example.com")
            .withScholarship("Global Merit").withApplicationStatus("pending").build();
    public static final Applicant FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withEmail("lydia@example.com")
            .withScholarship("Global Merit").withApplicationStatus("pending").build();
    public static final Applicant GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442").withEmail("anna@example.com")
            .withScholarship("Global Merit").withApplicationStatus("accepted").build();

    // Manually added
    public static final Applicant HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424").withEmail("stefan@example.com")
            .withScholarship("Global Merit").withApplicationStatus("pending").build();
    public static final Applicant IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131").withEmail("hans@example.com")
            .withScholarship("Global Merit").withApplicationStatus("pending").build();

    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withScholarship(VALID_SCHOLARSHIP_AMY)
            .withApplicationStatus(VALID_APPLICATION_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Applicant BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withScholarship(VALID_SCHOLARSHIP_BOB)
            .withApplicationStatus(VALID_APPLICATION_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code TrackAScholar} with all the typical persons.
     */
    public static TrackAScholar getTypicalAddressBook() {
        TrackAScholar ab = new TrackAScholar();
        for (Applicant applicant : getTypicalPersons()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    public static List<Applicant> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
