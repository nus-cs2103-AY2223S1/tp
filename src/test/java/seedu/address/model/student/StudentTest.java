package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOK_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERMEDIATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.AVA;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

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
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withNokPhone(VALID_NOK_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_INTERMEDIATE).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns true (same student by same phone number)
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
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
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different next of kin phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withNokPhone(VALID_NOK_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_INTERMEDIATE).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void execute_setClassSuccess() {
        Student student = new StudentBuilder(ALICE).build();
        student.setClass(new Class());
        assertEquals("", student.getAClass().classDateTime);
    }

    @Test
    public void execute_setDisplayClassSuccess() throws ParseException {
        Class validClass = new Class(LocalDate.of(2022, 10, 21),
                LocalTime.of(13, 0), LocalTime.of(14, 0));

        Student unattendedStudent = new StudentBuilder(ALICE).withMark(Boolean.FALSE)
                .withDisplayDate("2022-10-21 1300-1400").build();
        unattendedStudent.setClass(new Class());
        unattendedStudent.setDisplayClass(validClass);
        assertEquals("", unattendedStudent.getDisplayedClass().classDateTime);

        Student attendedStudent = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();
        attendedStudent.setDisplayClass(validClass);
        assertEquals("2022-10-21 1300-1400", attendedStudent.getDisplayedClass().classDateTime);
    }

    @Test
    public void studentDisplayDateComparisonTest() {
        Student alice = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();
        Student bob = new StudentBuilder(BOB).withMark(Boolean.TRUE).build();

        Class aliceClass = VALID_MORNING_CLASS;
        Class bobClass = VALID_AFTERNOON_CLASS;

        alice.setDisplayClass(aliceClass);
        bob.setDisplayClass(bobClass);

        // Should return -1 since Alice's class is before Bob's
        assertEquals(-1, alice.compareToByDisplayClass(bob));

        //Should return 1 since Bob's class is after Alice's
        assertEquals(1, bob.compareToByDisplayClass(alice));
    }

    @Test
    public void execute_updateDisplayClassSuccess() {
        Student alice = new StudentBuilder(ALICE).withMark(Boolean.TRUE).build();

        // Simulate that this particular date is the schedule list date
        LocalDate particularDate = LocalDate.of(2022, 10, 21);
        Class aliceClass = new Class(particularDate, LocalTime.of(11, 0),
                LocalTime.of(12, 0));
        alice.setClass(aliceClass);

        alice.updateDisplayClass(particularDate);

        // Alice's display class should be the current class
        assertEquals(aliceClass, alice.getDisplayedClass());

        // Alice's attendance should reset
        assertTrue(!alice.getMarkStatus().isMarked());
    }

    @Test
    public void execute_multipleClassesPerDayCheck() {
        Student alice = new StudentBuilder(ALICE).withMark(Boolean.FALSE).build();
        alice.setClass(VALID_MORNING_CLASS);

        // Alice does not have multiple classes on the same day since she is not marked
        assertFalse(alice.hasMultipleClasses());

        Student bob = new StudentBuilder(BOB).withMark(Boolean.TRUE).build();
        bob.setClass(VALID_AFTERNOON_CLASS);
        bob.setDisplayClass(VALID_MORNING_CLASS);

        // Bob has a displayed class and next class on the same day
        assertTrue(bob.hasMultipleClasses());
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
    public void compareToByClassAscTest() {
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
            bob = new StudentBuilder(BOB).withClass("2022-10-11 0400-0500").build();
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
}
