package tuthub.testutil;

import static tuthub.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static tuthub.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tuthub.model.AddressBook;
import tuthub.model.tutor.Tutor;

/**
 * A utility class containing a list of {@code Tutor} objects to be used in tests.
 */
public class TypicalTutors {

    public static final Tutor ALICE = new TutorBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withModule("CS2100").withYear("3").withPhone("94351253").withStudentId("A1234569L")
            .withTags("friends").build();
    public static final Tutor BENSON = new TutorBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withModule("CS2100").withYear("3").withStudentId("A0000009Y")
            .withTags("owesMoney", "friends").build();
    public static final Tutor CARL = new TutorBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withModule("CS2100").withYear("3").withStudentId("A9875647U")
            .withAddress("wall street").build();
    public static final Tutor DANIEL = new TutorBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withModule("CS2100").withYear("3").withStudentId("A9345647M")
            .withAddress("10th street").withTags("friends").build();
    public static final Tutor ELLE = new TutorBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withModule("CS2100").withYear("3").withStudentId("A8768974P")
            .withAddress("michegan ave").build();
    public static final Tutor FIONA = new TutorBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withModule("CS2100").withYear("3").withStudentId("A9870921H")
            .withAddress("little tokyo").build();
    public static final Tutor GEORGE = new TutorBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withModule("CS2100").withYear("3").withStudentId("A0673201Y")
            .withAddress("4th street").build();

    // Manually added
    public static final Tutor HOON = new TutorBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withModule("cs2103t").withYear("3").withStudentId("A1234758N")
            .withAddress("little india").build();
    public static final Tutor IDA = new TutorBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withModule("cs2100").withYear("4").withStudentId("A0965302K")
            .withAddress("chicago ave").build();

    // Manually added - Tutor's details found in {@code CommandTestUtil}
    public static final Tutor AMY = new TutorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withModule(VALID_MODULE_AMY).withYear(VALID_YEAR_AMY)
            .withStudentId(VALID_STUDENTID_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Tutor BOB = new TutorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withModule(VALID_MODULE_BOB).withYear(VALID_YEAR_BOB)
            .withStudentId(VALID_STUDENTID_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTutors() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tutors.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tutor tutor : getTypicalTutors()) {
            ab.addTutor(tutor);
        }
        return ab;
    }

    public static List<Tutor> getTypicalTutors() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
