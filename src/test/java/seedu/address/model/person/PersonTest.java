package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AVA;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    public static final String EDITED_PHONE = "90008000";
    public static final Class VALID_MORNING_CLASS = new Class(LocalDate.of(2022, 10, 21),
            LocalTime.of(11, 0), LocalTime.of(12, 0));
    public static final Class VALID_AFTERNOON_CLASS = new Class(LocalDate.of(2022, 10, 21),
            LocalTime.of(13, 0), LocalTime.of(14, 0));

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns true (same person by same phone number)
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // different phone number -> returns false
        editedBob = new PersonBuilder(BOB).withPhone(EDITED_PHONE).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // different next of kin phone number -> returns true
        editedBob = new PersonBuilder(BOB).withNokPhone(EDITED_PHONE).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void execute_setClassSuccess() {
        Person person = new PersonBuilder(ALICE).build();
        person.setClass(new Class());
        assertEquals("", person.getAClass().classDateTime);
    }

    @Test
    public void execute_setDisplayClassSuccess() throws ParseException {
        Class validClass = new Class(LocalDate.of(2022, 10, 21),
                LocalTime.of(13, 0), LocalTime.of(14, 0));

        Person unattendedPerson = new PersonBuilder(ALICE).withMark(Boolean.FALSE)
                .withDisplayDate("2022-10-21 1300-1400").build();
        unattendedPerson.setClass(new Class());
        unattendedPerson.setDisplayClass(validClass);
        assertEquals("", unattendedPerson.getDisplayedClass().classDateTime);

        Person attendedPerson = new PersonBuilder(ALICE).withMark(Boolean.TRUE).build();
        attendedPerson.setDisplayClass(validClass);
        assertEquals("2022-10-21 1300-1400", attendedPerson.getDisplayedClass().classDateTime);
    }

    @Test
    public void personDisplayDateComparisonTest() {
        Person alice = new PersonBuilder(ALICE).withMark(Boolean.TRUE).build();
        Person bob = new PersonBuilder(BOB).withMark(Boolean.TRUE).build();

        Class aliceClass = VALID_MORNING_CLASS;
        Class bobClass = VALID_AFTERNOON_CLASS;

        alice.setDisplayClass(aliceClass);
        bob.setDisplayClass(bobClass);

        // Should return -1 since Alice's class is before Bob's
        assertEquals(-1, alice.compareTo(bob));

        //Should return 1 since Bob's class is after Alice's
        assertEquals(1, bob.compareTo(alice));
    }

    @Test
    public void execute_updateDisplayClassSuccess() {
        Person alice = new PersonBuilder(ALICE).withMark(Boolean.TRUE).build();

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
        Person alice = new PersonBuilder(ALICE).withMark(Boolean.FALSE).build();
        alice.setClass(VALID_MORNING_CLASS);

        // Alice does not have multiple classes on the same day since she is not marked
        assertFalse(alice.hasMultipleClasses());

        Person bob = new PersonBuilder(BOB).withMark(Boolean.TRUE).build();
        bob.setClass(VALID_AFTERNOON_CLASS);
        bob.setDisplayClass(VALID_MORNING_CLASS);

        // Bob has a displayed class and next class on the same day
        assertTrue(bob.hasMultipleClasses());
    }

    @Test
    public void owesMoneyTest() {
        Person debtor = AVA;
        assertTrue(debtor.isOwingMoney());

        Person nonDebtor = new PersonBuilder(AVA).withMoneyOwed(0).build();
        assertFalse(nonDebtor.isOwingMoney());
    }

}
