package jeryl.fyp.testutil;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
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

    public static final Student ALICE = new StudentBuilder().withStudentName("Alice Pauline")
            .withEmail("alice@example.com").withStudentId("A1351253P")
            .withProjectName("Using CRISP to generate crisps")
            .withProjectStatus("YTS")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withStudentName("Benson Meier")
            .withEmail("johnd@example.com").withStudentId("A1765432B")
            .withProjectName("Neural Networks and ML")
            .withProjectStatus("IP")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withStudentName("Carl Kurz").withStudentId("A1352563C")
            .withEmail("heinz@example.com").withProjectName("About Decision Trees").build();
    public static final Student DANIEL = new StudentBuilder().withStudentName("Daniel Meier")
            .withStudentId("A1652533D").withEmail("cornelia@example.com")
            .withProjectName("Mathematics in Games").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withStudentName("Elle Meyer").withStudentId("A1482224E")
            .withEmail("werner@example.com").withProjectName("AB3 in a Nutshell").build();
    public static final Student FIONA = new StudentBuilder().withStudentName("Fiona Kunz").withStudentId("A1482427F")
            .withEmail("lydia@example.com").withProjectName("Hello World in 42 Languages").build();
    public static final Student GEORGE = new StudentBuilder().withStudentName("George Best").withStudentId("A1482442G")
            .withEmail("anna@example.com").withProjectName("Why George Best is the Best").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withStudentName("Hoon Meier").withStudentId("A1482424H")
            .withEmail("stefan@example.com").withProjectName("Robotics").build();
    public static final Student IDA = new StudentBuilder().withStudentName("Ida Mueller").withStudentId("A1482131I")
            .withEmail("hans@example.com").withProjectName("computer network").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withStudentName(VALID_STUDENT_NAME_AMY)
            .withStudentId(VALID_STUDENT_ID_AMY).withEmail(VALID_EMAIL_AMY)
            .withProjectName(VALID_PROJECT_NAME_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withStudentName(VALID_STUDENT_NAME_BOB)
            .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_BOB)
            .withProjectName(VALID_PROJECT_NAME_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

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
