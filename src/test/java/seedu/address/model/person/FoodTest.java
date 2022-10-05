package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.address.testutil.FoodBuilder.DEFAULT_LATER_TIME;
import static seedu.address.testutil.TypicalPersons.APPLE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.GRAPES;

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
        Food editedApple = new FoodBuilder(APPLE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSamePerson(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new FoodBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.isSamePerson(editedApple));

        // name differs in case, all other attributes same -> returns false
        Food editedBob = new FoodBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new FoodBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void getEarliestMealTag() {
        // breakfast
        Food editedGrapes = new FoodBuilder(GRAPES).withTags("breakfast").build();
        assertEquals("B", editedGrapes.getEarliestMealTag());

        // lunch
        editedGrapes = new FoodBuilder(GRAPES).withTags("lunch").build();
        assertEquals("L", editedGrapes.getEarliestMealTag());

        // dinner
        editedGrapes = new FoodBuilder(GRAPES).withTags("dinner").build();
        assertEquals("D", editedGrapes.getEarliestMealTag());

        // no tag
        editedGrapes = new FoodBuilder(GRAPES).build();
        assertEquals("X", editedGrapes.getEarliestMealTag());
    }

    @Test
    public void isAfter() {
        Food editedApple = new FoodBuilder(APPLE).withDateTime(DEFAULT_EARLIER_TIME).build();
        Food editedGrapes = new FoodBuilder(GRAPES).withDateTime(DEFAULT_LATER_TIME).build();
        assertTrue(editedGrapes.isAfter(editedApple));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Food appleCopy = new FoodBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different person -> returns false
        assertFalse(APPLE.equals(BOB));

        // different name -> returns false
        Food editedApple = new FoodBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new FoodBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
