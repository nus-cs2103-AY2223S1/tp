package seedu.studmap.testutil;

import static seedu.studmap.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_GIT_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_GIT_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_HANDLE_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_HANDLE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.studmap.model.StudMap;
import seedu.studmap.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withId("A1234567A").withGitName("user1").withTeleHandle("@user1").withPhone("94351253")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withId("A1234567B").withGitName("user2")
            .withTeleHandle("@user2").withTags("owesMoney", "friends")
            .addAttended("T01", "T02")
            .addNotAttended("T03").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")

            .withEmail("heinz@example.com").withId("A1234567C").withGitName("user3")
            .withTeleHandle("@user3").withAddress("wall street").addAssignedMarked("A01", "A02")
            .addAssignedReceived("A03")
            .addAssignedNew("A04").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withId("A1234567D").withGitName("user4")
            .withTeleHandle("@user4").withAddress("10th street").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withId("A1234567E").withGitName("user5")
            .withTeleHandle("@user5").withAddress("michegan ave").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withId("A1234567F").withGitName("user6")
            .withTeleHandle("@user6").withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withId("A1234567G").withGitName("user7")
            .withTeleHandle("@user7").withAddress("4th street").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withId("A1234567H").withGitName("user8")
            .withTeleHandle("@user8").withAddress("little india").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withId("A1234567I").withGitName("user9")
            .withTeleHandle("@user9").withAddress("chicago ave").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withId(VALID_ID_AMY).withGitName(VALID_GIT_AMY).withTeleHandle(VALID_HANDLE_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withId(VALID_ID_BOB).withGitName(VALID_GIT_BOB).withTeleHandle(VALID_HANDLE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code StudMap} with all the typical students.
     */
    public static StudMap getTypicalStudMap() {
        StudMap sm = new StudMap();
        for (Student student : getTypicalStudents()) {
            sm.addStudent(student);
        }
        return sm;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
