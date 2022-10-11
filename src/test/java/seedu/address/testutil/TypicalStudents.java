package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

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
            .withStudentId("e0123456").withEmail("alice@example.com")
            .withPhone("94351253").withClassGroup("CS2030 Lab 2")
            .withTags("friends")
            .withAttendance("0")
            .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withStudentId("e0354264").withClassGroup("CS2040 Tutorial 32")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withAttendance("1").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withClassGroup("CS2030 Lab 2")
            .withPhone("95352563").withEmail("heinz@example.com").withStudentId("e0023682").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withClassGroup("CS2030 Lab 2")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withStudentId("e0822195").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withClassGroup("CS2040 Tutorial 32").withEmail("werner@example.com").withStudentId("e0747722").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withClassGroup("CS2040 Tutorial 32").withEmail("lydia@example.com").withStudentId("e0792769").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withClassGroup("CS2040 Tutorial 32").withEmail("anna@example.com").withStudentId("e0121822").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withStudentId("e0565321").withClassGroup("CS2103T Tutorial 15").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withStudentId("e0565221").withClassGroup("CS2103T Tutorial 15").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withStudentId(VALID_STUDENTID_AMY).withClassGroup(VALID_CLASS_GROUP_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withStudentId(VALID_STUDENTID_BOB).withClassGroup(VALID_CLASS_GROUP_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

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
