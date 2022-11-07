package jarvis.testutil;

import static jarvis.logic.commands.CommandTestUtil.VALID_MATRIC_NUM_AMY;
import static jarvis.logic.commands.CommandTestUtil.VALID_MATRIC_NUM_BOB;
import static jarvis.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static jarvis.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jarvis.model.Student;
import jarvis.model.StudentBook;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withMatricNum("A0000000F").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withMatricNum("A0345385G").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withMatricNum("A0532453D").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withMatricNum("A9353548C").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withMatricNum("A0573537F").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withMatricNum("A0385938L").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withMatricNum("A0395839D").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withMatricNum("A9348985U").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withMatricNum("A9384877B").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withMatricNum(VALID_MATRIC_NUM_AMY).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withMatricNum(VALID_MATRIC_NUM_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code StudentBook} with all the typical students.
     */
    public static StudentBook getTypicalStudentBook() {
        StudentBook sb = new StudentBook();
        for (Student student : getTypicalStudents()) {
            sb.addStudent(student);
        }
        return sb;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
