package foodwhere.model.stall;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.testutil.PersonBuilder;
import foodwhere.testutil.TypicalPersons;

public class StallTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Stall stall = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> stall.getDetails().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(TypicalPersons.ALICE.isSamePerson(TypicalPersons.ALICE));

        // null -> returns false
        assertFalse(TypicalPersons.ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Stall editedAlice =
                new PersonBuilder(TypicalPersons.ALICE)
                        .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withDetails(CommandTestUtil.VALID_DETAIL_HUSBAND)
                        .build();
        assertTrue(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TypicalPersons.ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Stall editedBob = new PersonBuilder(TypicalPersons.BOB)
                .withName(CommandTestUtil.VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(TypicalPersons.BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalPersons.BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Stall aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalPersons.ALICE.equals(5));

        // different stall -> returns false
        assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different name -> returns false
        Stall editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different details -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withDetails(CommandTestUtil.VALID_DETAIL_HUSBAND).build();
        assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }
}
