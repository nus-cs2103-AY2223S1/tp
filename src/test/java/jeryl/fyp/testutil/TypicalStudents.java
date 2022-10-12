package jeryl.fyp.testutil;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECTNAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECTNAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withStudentID("A1351253H").withProjectName("neural network")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withStudentID("A1765432D")
            .withProjectName("decision tree")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withStudentID("A1352563C")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withProjectName("B+ Tree").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withStudentID("A1652533D")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withProjectName("Soft Engineering").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withStudentID("A1482224E")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withProjectName("database design").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withStudentID("A1482427F")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withProjectName("rasterization in game development").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withStudentID("A1482442G")
            .withEmail("anna@example.com").withAddress("4th street")
            .withProjectName("programming language").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withStudentID("A1482424H")
            .withEmail("stefan@example.com").withAddress("little india")
            .withProjectName("Robotics").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withStudentID("A1482131I")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withProjectName("computer network").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withStudentID(VALID_STUDENTID_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withProjectName(VALID_PROJECTNAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withStudentID(VALID_STUDENTID_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withProjectName(VALID_PROJECTNAME_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code FypManager} with all the typical students.
     */
    public static FypManager getTypicalFypManager() {
        FypManager ab = new FypManager();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
