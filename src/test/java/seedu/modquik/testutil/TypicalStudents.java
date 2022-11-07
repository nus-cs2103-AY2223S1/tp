package seedu.modquik.testutil;

import static seedu.modquik.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_MODULE_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_NAME_TUT1;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modquik.model.ModQuik;
import seedu.modquik.model.student.Student;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withId("A0000000A")
            .withPhone("94351253").withEmail("alice@example.com")
            .withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withId("A0000000B")
            .withPhone("98765432").withEmail("johnd@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")

            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withId("A0000000C")
            .withPhone("95352563").withEmail("heinz@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")
            .build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withId("A0000000D").withPhone("87652533")
            .withEmail("cornelia@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")
            .withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withId("A0000000E")
            .withPhone("94821224")
            .withEmail("werner@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withId("A0000000F")
            .withPhone("94821427")
            .withEmail("lydia@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withId("A0000000G").withPhone("94824142")
            .withEmail("anna@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withId("A0000000H")
            .withPhone("84812424")
            .withEmail("stefan@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withId("A0000000I")
            .withPhone("84821131")
            .withEmail("hans@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withId(VALID_ID_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withTutorialModule(VALID_MODULE_TUT1)
            .withTutorialName(VALID_NAME_TUT1).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withId(VALID_ID_BOB).withTutorialModule(VALID_MODULE_TUT1)
            .withTutorialName(VALID_NAME_TUT1).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code ModQuik} with all the typical students.
     */
    public static ModQuik getTypicalModQuik() {
        ModQuik ab = new ModQuik();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
