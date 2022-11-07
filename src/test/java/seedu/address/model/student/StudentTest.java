package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.AVA;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    public static final String EDITED_PHONE = "90008000";
    public static final Class VALID_MORNING_CLASS = new Class(LocalDate.of(2022, 10, 21),
            LocalTime.of(11, 0), LocalTime.of(12, 0));
    public static final Class VALID_AFTERNOON_CLASS = new Class(LocalDate.of(2022, 10, 21),
            LocalTime.of(13, 0), LocalTime.of(14, 0));

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withNokPhone(CommandTestUtil.VALID_NOK_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(CommandTestUtil.VALID_TAG_INTERMEDIATE).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns true (same student by same phone number)
        editedAlice = new StudentBuilder(ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(BOB).withName(CommandTestUtil.VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameStudent(editedBob));

        // different phone number -> returns false
        editedBob = new StudentBuilder(BOB).withPhone(EDITED_PHONE).build();
        assertFalse(BOB.isSameStudent(editedBob));

        // different next of kin phone number -> returns true
        editedBob = new StudentBuilder(BOB).withNokPhone(EDITED_PHONE).build();
        assertTrue(BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different next of kin phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withNokPhone(CommandTestUtil.VALID_NOK_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StudentBuilder(ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(CommandTestUtil.VALID_TAG_INTERMEDIATE).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void execute_setClassSuccess() {
        Student student = new StudentBuilder(ALICE).build();
        student.setClass(new Class());
        assertEquals("", student.getAClass().classDateTime);
    }

    @Test
    public void execute_getEmptyClass() {
        Student student = new StudentBuilder(ALICE).build();
        student.setClass(null);
        assertTrue(student.getAClass().equals(new Class()));

    }

    @Test
    public void execute_multipleClassesPerDayCheck() {
        Student alice = new StudentBuilder(ALICE).withMark(Boolean.FALSE).build();
        alice.setClass(VALID_MORNING_CLASS);

        Student aliceDuplicate = new StudentBuilder(ALICE).withMark(Boolean.FALSE).build();
        aliceDuplicate.setClass(VALID_AFTERNOON_CLASS);

        // Alice does not have multiple classes on the same day since she is not marked
        assertFalse(alice.hasSameDateAs(aliceDuplicate));

        alice = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();
        alice.setClass(VALID_MORNING_CLASS);

        aliceDuplicate = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();
        aliceDuplicate.setClass(VALID_AFTERNOON_CLASS);

        assertTrue(alice.hasSameDateAs(aliceDuplicate));

        alice = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();
        alice.setClass(VALID_MORNING_CLASS);

    }

    @Test
    public void owesMoneyTest() {
        Student debtor = AVA;
        assertTrue(debtor.isOwingMoney());

        Student nonDebtor = new StudentBuilder(AVA).withMoneyOwed(0).build();
        assertFalse(nonDebtor.isOwingMoney());
    }

    @Test
    public void compareToByNameAscTest() {
        Student alice = new StudentBuilder(ALICE).build();
        Student bob = new StudentBuilder(BOB).build();
        assertTrue(alice.compareToByNameAsc(bob) < 0);
        assertTrue(alice.compareToByNameAsc(alice) == 0);
        assertTrue(bob.compareToByNameAsc(alice) > 0);
    }
    @Test
    public void compareToByNameDescTest() {
        Student alice = new StudentBuilder(ALICE).build();
        Student bob = new StudentBuilder(BOB).build();
        assertTrue(alice.compareToByNameDesc(bob) > 0);
        assertTrue(alice.compareToByNameDesc(alice) == 0);
        assertTrue(bob.compareToByNameDesc(alice) < 0);
    }

    @Test
    public void compareToByClassStartTimeAscTest() {
        Student alice;
        Student bob;
        Student ava;
        Student bobWithoutClass;
        try {
            alice = new StudentBuilder(ALICE).withClass("2022-10-11 0200-0400").build();
            bob = new StudentBuilder(BOB).withClass("2022-10-11 0400-0500").build();
            ava = new StudentBuilder(AVA).build();
            bobWithoutClass = new StudentBuilder(BOB).build();
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        // both with class fields initialized
        assertTrue(alice.compareToByClassStartTimeAsc(bob) < 0);
        assertTrue(alice.compareToByClassStartTimeAsc(alice) == 0);
        assertTrue(bob.compareToByClassStartTimeAsc(alice) > 0);

        // one is non-initialized
        assertTrue(alice.compareToByClassStartTimeAsc(ava) < 0);
        assertTrue(ava.compareToByClassStartTimeAsc(bob) > 0);

        // both class fields not initialized, this will never happen in the real case.
        assertTrue(ava.compareToByClassStartTimeAsc(bobWithoutClass) == 0);
        assertTrue(bobWithoutClass.compareToByClassStartTimeAsc(ava) == 0);
        assertTrue(ava.compareToByClassStartTimeAsc(ava) == 0);
    }
    @Test
    public void compareToByClassAscTest() {
        Student alice;
        Student bob;
        Student ava;
        Student bobWithoutClass;
        try {
            alice = new StudentBuilder(ALICE).withClass("2022-10-11 0200-0400").build();
            bob = new StudentBuilder(BOB).withClass("2022-10-12 0400-0500").build();
            ava = new StudentBuilder(AVA).build();
            bobWithoutClass = new StudentBuilder(BOB).build();
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        // both with class fields initialized
        assertTrue(alice.compareToByClassAsc(bob) < 0);
        assertTrue(alice.compareToByClassAsc(alice) == 0);
        assertTrue(bob.compareToByClassAsc(alice) > 0);

        // one is non-initialized
        assertTrue(alice.compareToByClassAsc(ava) < 0);
        assertTrue(ava.compareToByClassAsc(bob) > 0);

        // both class fields not initialized, now sort by name
        assertTrue(ava.compareToByClassAsc(bobWithoutClass) < 0);
        assertTrue(bobWithoutClass.compareToByClassAsc(ava) > 0);
        assertTrue(ava.compareToByClassAsc(ava) == 0);
    }

    @Test
    public void compareToByClassDescTest() {
        Student alice;
        Student bob;
        Student ava;
        Student bobWithoutClass;
        try {
            alice = new StudentBuilder(ALICE).withClass("2022-10-11 0200-0400").build();
            bob = new StudentBuilder(BOB).withClass("2022-10-12 0400-0500").build();
            ava = new StudentBuilder(AVA).build();
            bobWithoutClass = new StudentBuilder(BOB).build();
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        // both with class fields initialized
        assertTrue(alice.compareToByClassDesc(bob) > 0);
        assertTrue(alice.compareToByClassDesc(alice) == 0);
        assertTrue(bob.compareToByClassDesc(alice) < 0);

        // one is non-initialized
        assertTrue(alice.compareToByClassDesc(ava) < 0);
        assertTrue(ava.compareToByClassDesc(bob) > 0);

        // both class fields not initialized, now sort by name
        assertTrue(ava.compareToByClassDesc(bobWithoutClass) < 0);
        assertTrue(bobWithoutClass.compareToByClassDesc(ava) > 0);
        assertTrue(ava.compareToByClassDesc(ava) == 0);
    }

    @Test
    public void compareToByMoneyOwedAscTest() {
        Student alice = new StudentBuilder(ALICE).withMoneyOwed(100).build();
        Student ava = new StudentBuilder(AVA).withMoneyOwed(100).build();
        Student bob = new StudentBuilder(BOB).withMoneyOwed(200).build();

        assertTrue(alice.compareToByMoneyOwedAsc(ava) < 0);
        assertTrue(alice.compareToByMoneyOwedAsc(bob) < 0);
        assertTrue(ava.compareToByMoneyOwedAsc(bob) < 0);
    }

    @Test
    public void compareToByMoneyOwedDescTest() {
        Student alice = new StudentBuilder(ALICE).withMoneyOwed(100).build();
        Student ava = new StudentBuilder(AVA).withMoneyOwed(100).build();
        Student bob = new StudentBuilder(BOB).withMoneyOwed(200).build();

        assertTrue(alice.compareToByMoneyOwedDesc(ava) < 0);
        assertTrue(alice.compareToByMoneyOwedDesc(bob) > 0);
        assertTrue(ava.compareToByMoneyOwedDesc(bob) > 0);
    }

    @Test
    public void shareSameNumberAsNokTest() {
        Student alice = new StudentBuilder(ALICE).withPhone(CommandTestUtil.VALID_PHONE_AMY)
                .withNokPhone(CommandTestUtil.VALID_NOK_PHONE_BOB).build();
        assertFalse(alice.hasSharedPhone());

        Student bob = new StudentBuilder(BOB).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withNokPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        assertTrue(bob.hasSharedPhone());
    }

    @Test
    public void resetMarkStatusTest() {
        Student alice = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();
        assertTrue(alice.getMarkStatus().isMarked());

        alice.resetMarkStatus();
        assertFalse(alice.getMarkStatus().isMarked());
    }
}
