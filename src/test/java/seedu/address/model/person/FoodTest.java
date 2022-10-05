package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.APPLE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FoodBuilder;

public class FoodTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(APPLE.isSamePerson(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Food editedAlice = new FoodBuilder(APPLE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new FoodBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Food editedBob = new FoodBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new FoodBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Food aliceCopy = new FoodBuilder(APPLE).build();
        assertTrue(APPLE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different person -> returns false
        assertFalse(APPLE.equals(BOB));

        // different name -> returns false
        Food editedAlice = new FoodBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new FoodBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.equals(editedAlice));
    }
}
