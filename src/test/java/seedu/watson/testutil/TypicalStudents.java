package seedu.watson.testutil;

import static seedu.watson.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_REMARK_ICE_CREAM;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_STUDENTCLASS;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_SUBJECTHANDLER;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.watson.model.Database;
import seedu.watson.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
                                                          .withAddress("123, Jurong West Ave 6, #08-111")
                                                          .withEmail("alice@example.com")
                                                          .withPhone("94351253").withTags("friends")
                                                          .withStudentClass("1A")
                                                          .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                  + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                  + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                          .build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
                                                           .withAddress("311, Clementi Ave 2, #02-25")
                                                           .withEmail("johnd@example.com").withPhone("98765432")
                                                           .withTags("owesMoney", "friends")
                                                           .withStudentClass("1A")
                                                           .withAttendance("date/12-02-2022 attendance/1 "
                                                            + "%% date/13-02-2022 attendance/0")
                                                           .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                   + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                   + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                           .build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
                                                         .withPhone("95352563")
                                                         .withEmail("heinz@example.com").withAddress("wall street")
                                                         .withStudentClass("1A")
                                                         .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                 + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                 + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                         .build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
                                                           .withPhone("87652533")
                                                           .withEmail("cornelia@example.com").withAddress("10th street")
                                                           .withTags("friends")
                                                           .withStudentClass("1A").withRemarks("friendly")
                                                           .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                   + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                   + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                           .build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
                                                         .withPhone("9482224")
                                                         .withEmail("werner@example.com").withAddress("michegan ave")
                                                         .withStudentClass("1A")
                                                         .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                 + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                 + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                         .build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
                                                          .withPhone("9482427")
                                                          .withEmail("lydia@example.com").withAddress("little tokyo")
                                                          .withStudentClass("1A")
                                                          .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                  + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                  + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                          .build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
                                                           .withPhone("9482442")
                                                           .withEmail("anna@example.com").withAddress("4th street")
                                                           .withStudentClass("1A")
                                                           .withSubjectHandler("english: CA1:[80.0, 100.0, 0.2, 1.0], "
                                                                   + "CA2:[30.0, 56.0, 0.4, 2.0] %%math: CA1:[80.0, "
                                                                   + "100.0, 0.2, 1.0], CA2:[30.0, 56.0, 0.4, 2.0]")
                                                           .build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
                                                         .withPhone("8482424")
                                                         .withEmail("stefan@example.com").withAddress("little india")
                                                         .build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
                                                        .withPhone("8482131")
                                                        .withEmail("hans@example.com").withAddress("chicago ave")
                                                        .build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
                                                        .withPhone(VALID_PHONE_AMY)
                                                        .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                                                        .withTags(VALID_TAG_FRIEND)
                                                        .withStudentClass(VALID_STUDENTCLASS)
                                                        .withRemarks(VALID_REMARK_ICE_CREAM)
                                                        .withSubjectHandler(VALID_SUBJECTHANDLER)
                                                        .build();

    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
                                                        .withPhone(VALID_PHONE_BOB)
                                                        .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                                                        .withTags(VALID_TAG_FRIEND)
                                                        .withStudentClass(VALID_STUDENTCLASS)
                                                        .withRemarks(VALID_REMARK_ICE_CREAM)
                                                        .withSubjectHandler(VALID_SUBJECTHANDLER)
                                                        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {
    } // prevents instantiation

    /**
     * Returns an {@code Database} with all the typical persons.
     */
    public static Database getTypicalDatabase() {
        Database database = new Database();
        for (Student student : getTypicalPersons()) {
            database.addPerson(student);
        }
        return database;
    }

    public static List<Student> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
