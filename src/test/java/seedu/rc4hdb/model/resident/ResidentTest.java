package seedu.rc4hdb.model.resident;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_GENDER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_HOUSE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_MATRIC_NUMBER_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ROOM_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BOB;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.testutil.ResidentBuilder;

public class ResidentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Resident person = new ResidentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameResident() {
        // same object -> returns true
        assertTrue(ALICE.isSameResident(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameResident(null));

        // same name, all other attributes different -> returns true
        Resident editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withRoom(VALID_ROOM_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameResident(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ResidentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameResident(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Resident editedBob = new ResidentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameResident(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ResidentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameResident(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Resident aliceCopy = new ResidentBuilder(ALICE).build();
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
        Resident editedAlice = new ResidentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ResidentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different room -> returns false
        editedAlice = new ResidentBuilder(ALICE).withRoom(VALID_ROOM_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different gender -> returns false
        editedAlice = new ResidentBuilder(ALICE).withGender(VALID_GENDER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different house -> returns false
        editedAlice = new ResidentBuilder(ALICE).withHouse(VALID_HOUSE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different matricNumber -> returns false
        editedAlice = new ResidentBuilder(ALICE).withMatricNumber(VALID_MATRIC_NUMBER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ResidentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

}
