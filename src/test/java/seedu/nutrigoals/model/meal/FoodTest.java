package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_EARLIER_TIME;
import static seedu.nutrigoals.testutil.FoodBuilder.DEFAULT_LATER_TIME;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.BOB;
import static seedu.nutrigoals.testutil.TypicalFoods.GRAPES;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.testutil.FoodBuilder;

public class FoodTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }

    @Test
    public void isSameFood() {
        // same object -> returns true
        assertTrue(APPLE.isSameFood(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameFood(null));

        // same name, all other attributes different -> returns true
        Food editedApple = new FoodBuilder(APPLE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSameFood(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new FoodBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.isSameFood(editedApple));

        // name differs in case, all other attributes same -> returns false
        Food editedBob = new FoodBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameFood(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new FoodBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameFood(editedBob));
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

        // different food -> returns false
        assertFalse(APPLE.equals(BOB));

        // different name -> returns false
        Food editedApple = new FoodBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new FoodBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
