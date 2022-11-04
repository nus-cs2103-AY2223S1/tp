package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.commons.ModuleCode;
import seedu.address.model.commons.Venue;
import seedu.address.model.datetime.WeeklyTimeslot;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Student ALICE = new PersonBuilder().withName("Alice Pauline")
            .withId("A0000000A")
            .withPhone("94351253").withEmail("alice@example.com")
            .withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")
            .withTags("friends").build();
    public static final Student BENSON = new PersonBuilder().withName("Benson Meier")
            .withId("A0000000B")
            .withPhone("98765432").withEmail("johnd@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")

            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new PersonBuilder().withName("Carl Kurz")
            .withId("A0000000C")
            .withPhone("95352563").withEmail("heinz@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")
            .build();
    public static final Student DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withId("A0000000D").withPhone("87652533")
            .withEmail("cornelia@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A")
            .withTags("friends").build();
    public static final Student ELLE = new PersonBuilder().withName("Elle Meyer")
            .withId("A0000000E")
            .withPhone("94821224")
            .withEmail("werner@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();
    public static final Student FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withId("A0000000F")
            .withPhone("94821427")
            .withEmail("lydia@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();
    public static final Student GEORGE = new PersonBuilder().withName("George Best")
            .withId("A0000000G").withPhone("94824142")
            .withEmail("anna@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();

    // Manually added
    public static final Student HOON = new PersonBuilder().withName("Hoon Meier")
            .withId("A0000000H")
            .withPhone("84812424")
            .withEmail("stefan@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();
    public static final Student IDA = new PersonBuilder().withName("Ida Mueller")
            .withId("A0000000I")
            .withPhone("84821131")
            .withEmail("hans@example.com").withTelegram("alice")
            .withTutorialModule("CS2103T").withTutorialName("W17")
            .withAttendance("0").withParticipation("0").withGrade("A").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new PersonBuilder().withName(VALID_NAME_AMY).withId(VALID_ID_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withTutorialModule(VALID_MODULE_TUT1)
            .withTutorialName(VALID_NAME_TUT1).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withId(VALID_ID_BOB).withTutorialModule(VALID_MODULE_TUT1)
            .withTutorialName(VALID_NAME_TUT1).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalPersons()) {
            ab.addPerson(student);
        }
        ab.addTutorial(new Tutorial(new TutorialName("W17"), new ModuleCode("CS2103T"), new Venue("Zoom"),
                WeeklyTimeslot.fromFormattedString("1", "15:00", "17:00")));
        return ab;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
