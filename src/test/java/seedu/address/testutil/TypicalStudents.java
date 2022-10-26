package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESPONSE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESPONSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withTelegram("@AlicePauline").withEmail("alice@example.com")
            .withResponse("2")
            .withAttendance("1").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withTelegram("@bensonM")
            .withEmail("johnd@example.com").withResponse("1")
            .withAttendance("2").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withTelegram("@Carl")
            .withEmail("heinz@example.com").withResponse("4").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withTelegram("@daniell")
            .withEmail("cornelia@example.com").withResponse("2").withAttendance("3").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withTelegram("@Ellee")
            .withEmail("werner@example.com").withResponse("0").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withTelegram("@FionaK")
            .withEmail("lydia@example.com").withAttendance("5").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withTelegram("@GeorgeBest")
            .withEmail("anna@example.com").withAttendance("4").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withTelegram("@hoonnn")
            .withEmail("stefan@example.com").withResponse("2").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withTelegram("@idaaa")
            .withEmail("hans@example.com").withResponse("3").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withEmail(VALID_EMAIL_AMY).withResponse(VALID_RESPONSE_AMY).withAttendance(VALID_ATTENDANCE_BOB)
            .withHelpTag(false).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withEmail(VALID_EMAIL_BOB).withResponse(VALID_RESPONSE_BOB).withAttendance(VALID_ATTENDANCE_BOB)
            .withHelpTag(true).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

