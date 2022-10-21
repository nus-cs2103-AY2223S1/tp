package seedu.classify.testutil;

import static seedu.classify.logic.commands.CommandTestUtil.VALID_CLASS_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_CLASS_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_2;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PARENT_NAME_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.classify.model.StudentRecord;
import seedu.classify.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder()
            .withStudentName("Alice Pauline")
            .withId("123A")
            .withClassName("4A1")
            .withParentName("May Pauline")
            .withEmail("alice.pauline@gmail.com")
            .withPhone("94351253")
            .withExams("CA2 90", "SA1 60").build();
    public static final Student BENSON = new StudentBuilder()
            .withStudentName("Benson Meier")
            .withId("345B")
            .withClassName("4A2")
            .withParentName("Johnson Meier")
            .withPhone("98765432")
            .withEmail("bensonmeier@gmail.com")
            .withExams("CA1 90", "SA2 60").build();
    public static final Student CARL = new StudentBuilder()
            .withStudentName("Carl Kurz")
            .withId("567F")
            .withClassName("4B1")
            .withParentName("Kenn Kurz")
            .withPhone("95352563")
            .withEmail("carl@hotmail.com").build();
    public static final Student DANIEL = new StudentBuilder()
            .withStudentName("Daniel Meier")
            .withId("678D")
            .withClassName("3A1")
            .withParentName("Benn Meier")
            .withPhone("87652533")
            .withEmail("daniel.meier@gmail.com")
            .withExams("CA1 50", "CA2 70", "SA1 90", "SA2 60").build();
    public static final Student ELLE = new StudentBuilder()
            .withStudentName("Elle Meyer")
            .withId("890A")
            .withClassName("3A1")
            .withParentName("James Meyer")
            .withPhone("9482224")
            .withEmail("elle@gmail.com").build();
    public static final Student FIONA = new StudentBuilder()
            .withStudentName("Fiona Kunz")
            .withId("789C")
            .withClassName("Charity 1")
            .withParentName("Gordon Kunz")
            .withPhone("9482427")
            .withEmail("kunz.fiona@gmail.com").build();
    public static final Student GEORGE = new StudentBuilder()
            .withStudentName("George Best")
            .withId("912B")
            .withClassName("17S68")
            .withParentName("Henry Best")
            .withPhone("9482442")
            .withEmail("georgebest@example.com").build();

    // Manually added
    public static final Student HOON = new StudentBuilder()
            .withStudentName("Hoon Meier")
            .withId("562B")
            .withClassName("4O1")
            .withParentName("Denise Meier")
            .withPhone("8482424")
            .withEmail("hoon@yahoo.com").build();
    public static final Student IDA = new StudentBuilder()
            .withStudentName("Ida Mueller")
            .withId("784C")
            .withClassName("4O1")
            .withParentName("Nathan Mueller")
            .withPhone("8482131")
            .withEmail("idamueller@gmail.com").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder()
            .withStudentName(VALID_STUDENT_NAME_AMY)
            .withId(VALID_ID_AMY)
            .withClassName(VALID_CLASS_AMY)
            .withParentName(VALID_PARENT_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withExams().build();
    public static final Student BOB = new StudentBuilder()
            .withStudentName(VALID_STUDENT_NAME_BOB)
            .withId(VALID_ID_BOB)
            .withClassName(VALID_CLASS_BOB)
            .withParentName(VALID_PARENT_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withExams(VALID_EXAM_2).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns a {@code StudentRecord} with all the typical persons.
     */
    public static StudentRecord getTypicalStudentRecord() {
        StudentRecord sr = new StudentRecord();
        for (Student person : getTypicalStudent()) {
            sr.addStudent(person);
        }
        return sr;
    }

    public static List<Student> getTypicalStudent() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
