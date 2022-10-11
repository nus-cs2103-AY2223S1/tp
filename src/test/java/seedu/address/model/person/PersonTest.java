package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
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

    }

    @Test
    public void allEqualsExceptClass() throws ParseException {
        Person billy = new PersonBuilder().withName("Billy").withPhone("81234567")
                .withEmail("Billy@gmail.com").withAddress("Street 20 Building A").withClass("2022-05-05 1200-1400")
                .withMoneyOwed(0).withMoneyPaid(0).withAdditionalNotes("").build();
        Person billyWithDifferentClass = new PersonBuilder().withName("Billy").withPhone("81234567")
                .withEmail("Billy@gmail.com").withAddress("Street 20 Building A").withClass("2022-05-04 1100-1200")
                .withMoneyOwed(0).withMoneyPaid(0).withAdditionalNotes("").build();
        Person billyWithDifferentPhone = new PersonBuilder().withName("Billy").withPhone("91234567")
                .withEmail("Billy@gmail.com").withAddress("Street 20 Building A").withClass("2022-05-04 1100-1200")
                .withMoneyOwed(0).withMoneyPaid(0).withAdditionalNotes("").build();

        // same object -> returns true
        assertTrue(billy.allEqualsExceptClass(billy));

        // different class -> returns true
        assertTrue(billy.allEqualsExceptClass(billyWithDifferentClass));

        // different phone -> returns false
        assertFalse(billy.allEqualsExceptClass(billyWithDifferentPhone));

        // compared with null -> returns false
        assertFalse(billy.allEqualsExceptClass(null));
    }

}
