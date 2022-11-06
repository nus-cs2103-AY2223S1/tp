package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DAVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIFFANI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIFFANI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DAVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIFFANI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalTasks.TASK2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withTutorialGroup("T01")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withTutorialGroup("T01")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withTutorialGroup("T02")
            .withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withTutorialGroup("T02")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withTutorialGroup("T03")
            .withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withTutorialGroup("T03")
            .withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND)
            .build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    // Students with tutorial group and no tags
    public static final Student TIFFANI = new StudentBuilder().withName(VALID_NAME_TIFFANI)
            .withPhone(VALID_PHONE_TIFFANI).withEmail(VALID_EMAIL_TIFFANI)
            .build();

    public static final Student DAVE = new StudentBuilder().withName(VALID_NAME_DAVE)
            .withPhone(VALID_PHONE_DAVE).withEmail(VALID_EMAIL_DAVE)
            .build();
    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        ab.addTutorialGroup(new TutorialGroup("T01"));
        ab.addTutorialGroup(new TutorialGroup("T02"));
        ab.addTutorialGroup(new TutorialGroup("T03"));

        ab.addTask(new TaskBuilder().build());
        ab.addTask(new TaskBuilder(TASK2).build());

        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
