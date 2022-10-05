package foodwhere.model.stall;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.CommandTestUtil;
import foodwhere.testutil.StallBuilder;
import foodwhere.testutil.TypicalStalls;

public class StallTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Stall stall = new StallBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> stall.getDetails().remove(0));
    }

    @Test
    public void isSameStall() {
        // same object -> returns true
        assertTrue(TypicalStalls.ALICE.isSameStall(TypicalStalls.ALICE));

        // null -> returns false
        assertFalse(TypicalStalls.ALICE.isSameStall(null));

        // same name, all other attributes different -> returns true
        Stall editedAlice =
                new StallBuilder(TypicalStalls.ALICE)
                        .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withDetails(CommandTestUtil.VALID_DETAIL_HUSBAND)
                        .build();
        assertTrue(TypicalStalls.ALICE.isSameStall(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StallBuilder(TypicalStalls.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TypicalStalls.ALICE.isSameStall(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Stall editedBob = new StallBuilder(TypicalStalls.BOB)
                .withName(CommandTestUtil.VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(TypicalStalls.BOB.isSameStall(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_BOB + " ";
        editedBob = new StallBuilder(TypicalStalls.BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalStalls.BOB.isSameStall(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Stall aliceCopy = new StallBuilder(TypicalStalls.ALICE).build();
        assertTrue(TypicalStalls.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalStalls.ALICE.equals(TypicalStalls.ALICE));

        // null -> returns false
        assertFalse(TypicalStalls.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalStalls.ALICE.equals(5));

        // different stall -> returns false
        assertFalse(TypicalStalls.ALICE.equals(TypicalStalls.BOB));

        // different name -> returns false
        Stall editedAlice = new StallBuilder(TypicalStalls.ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(TypicalStalls.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StallBuilder(TypicalStalls.ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(TypicalStalls.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StallBuilder(TypicalStalls.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(TypicalStalls.ALICE.equals(editedAlice));

        // different details -> returns false
        editedAlice = new StallBuilder(TypicalStalls.ALICE).withDetails(CommandTestUtil.VALID_DETAIL_HUSBAND).build();
        assertFalse(TypicalStalls.ALICE.equals(editedAlice));
    }
}
