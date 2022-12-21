
package seedu.masslinkers.testutil;

import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_AI;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withTelegram("alice").withEmail("alice@example.com")
            .withPhone("94351253").withGitHub("alifur").withInterests("AI")
            .withMods("CS2100", "CS1231S").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withTelegram("bensonhaha").withGitHub("benji")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withInterests("AI", "SWE").withMods("CS2100", "CS1231S").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withGitHub("carl69")
            .withTelegram("magcarlsen").withMods("CS2100").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withTelegram("dannylim").withGitHub("dashi")
            .withInterests("AI").withMods("CS2100").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withGitHub("goldl8ol").withTelegram("ellie")
            .withMods("CS2100", "CS1231S").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withGitHub("fiona").withTelegram("fionalim")
            .withMods("CS2100").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withTelegram("georgesim").withGitHub("pizzac0der")
            .withMods("CS2100").build();

    // Only optional fields filled
    public static final Student TOM = new StudentBuilder().withName("Tom").withTelegram("tommm").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withTelegram("hoonmeier").withGitHub("coderhoon")
            .withMods("CS2100").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withGitHub("ida23").withPhone("8482131")
            .withEmail("hans@example.com").withTelegram("idamul").withMods("CS2100").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY).withGitHub(VALID_GITHUB_AMY)
            .withInterests(VALID_INTEREST_AI).withMods("CS2100")
            .build();

    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withGitHub(VALID_GITHUB_BOB)
            .withInterests(VALID_INTEREST_SWE, VALID_INTEREST_AI).withMods("CS2100")
            .build();
    public static final Student BOB_WITHOUT_AI = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withGitHub(VALID_GITHUB_BOB).withInterests(VALID_INTEREST_SWE).withMods("CS2100").build();
    public static final Student BOB_WITHOUT_INTERESTS = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withGitHub(VALID_GITHUB_BOB).withMods("CS2100").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code MassLinkers} with all the typical students.
     */
    public static MassLinkers getTypicalMassLinkers() {
        MassLinkers ab = new MassLinkers();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
