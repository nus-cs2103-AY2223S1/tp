package friday.testutil;

import static friday.logic.commands.CommandTestUtil.VALID_CONSULTATION_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_CONSULTATION_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_MASTERYCHECK_DATE_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_MASTERYCHECK_DATE_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static friday.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static friday.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static friday.logic.commands.CommandTestUtil.VALID_TELEGRAMHANDLE_AMY;
import static friday.logic.commands.CommandTestUtil.VALID_TELEGRAMHANDLE_BOB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import friday.model.Friday;
import friday.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    // Mastery Checks of these Students have not been marked as passed
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withMasteryCheck(LocalDate.of(2022, 2, 17), false)
            .withConsultation(LocalDate.of(2022, 2, 22))
            .withTelegramHandle("al1ce").withRemark("She loves CS1101S")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withMasteryCheck(LocalDate.of(2022, 8, 17), false)
            .withRemark("He can't take beer!").withConsultation(LocalDate.of(2022, 9, 17))
            .withTelegramHandle("benson123").withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withTelegramHandle("carl_isle")
            .withConsultation(LocalDate.of(2022, 11, 17))
            .withMasteryCheck(LocalDate.of(2090, 11, 12), false).build();
    // Mastery Checks of these Students have been marked as passed
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withTelegramHandle("d4ni3l")
            .withConsultation(LocalDate.of(2022, 1, 21))
            .withMasteryCheck(LocalDate.of(2022, 4, 2), true)
            .withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withTelegramHandle("e33elle33")
            .withConsultation(LocalDate.of(2022, 9, 7))
            .withMasteryCheck(LocalDate.of(2022, 1, 1), true).withTags("friends").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withTelegramHandle("fionakunz")
            .withConsultation(LocalDate.of(2022, 2, 20))
            .withMasteryCheck(LocalDate.of(2022, 3, 27), true)
            .withTags("friends").build();
    // empty Mastery Check
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withTelegramHandle("imthe_best")
            .withConsultation(LocalDate.of(2022, 12, 17)).withEmptyMasteryCheck()
            .withTags("friends").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withTelegramHandle("hoonigan1")
            .withConsultation(LocalDate.of(2022, 2, 23))
            .withMasteryCheck(LocalDate.of(2020, 2, 17), false)
            .withTags("friends").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withTelegramHandle("id4mueller")
            .withConsultation(LocalDate.of(2024, 2, 17))
            .withMasteryCheck(LocalDate.of(2024, 2, 7), false)
            .withTags("friends").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withTelegramHandle(VALID_TELEGRAMHANDLE_AMY).withConsultation(VALID_CONSULTATION_AMY)
            .withMasteryCheck(VALID_MASTERYCHECK_DATE_AMY, false).withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withTelegramHandle(VALID_TELEGRAMHANDLE_BOB).withConsultation(VALID_CONSULTATION_BOB)
            .withMasteryCheck(VALID_MASTERYCHECK_DATE_BOB, false).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code Friday} with all the typical students.
     */
    public static Friday getTypicalFriday() {
        Friday friday = new Friday();
        for (Student student : getTypicalStudents()) {
            friday.addStudent(student);
        }
        return friday;
    }

    public static Friday getTypicalFridayForTest() {
        Friday ab = new Friday();
        for (Student student : getTypicalStudentsForTest()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
    public static List<Student> getTypicalStudentsForTest() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON));
    }
}
