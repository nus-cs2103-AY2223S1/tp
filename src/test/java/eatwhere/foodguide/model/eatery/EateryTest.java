package eatwhere.foodguide.model.eatery;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_CUISINE_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;

public class EateryTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Eatery eatery = new EateryBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> eatery.getTags().remove(0));
    }

    @Test
    public void isSameEatery() {
        // same object -> returns true
        assertTrue(TypicalEateries.ALICE.isSameEatery(TypicalEateries.ALICE));

        // null -> returns false
        assertFalse(TypicalEateries.ALICE.isSameEatery(null));

        // same name, same location, all other attributes different -> returns true
        Eatery alsoEditedAlice = new EateryBuilder(TypicalEateries.ALICE)
                .withPrice(VALID_PRICE_BOB).withCuisine(VALID_CUISINE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(TypicalEateries.ALICE.isSameEatery(alsoEditedAlice));

        // same name, all other attributes different -> returns false
        Eatery editedAlice = new EateryBuilder(TypicalEateries.ALICE)
                .withPrice(VALID_PRICE_BOB).withCuisine(VALID_CUISINE_BOB)
                .withLocation(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(TypicalEateries.ALICE.isSameEatery(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EateryBuilder(TypicalEateries.ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(TypicalEateries.ALICE.isSameEatery(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Eatery editedBob = new EateryBuilder(TypicalEateries.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(TypicalEateries.BOB.isSameEatery(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EateryBuilder(TypicalEateries.BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(TypicalEateries.BOB.isSameEatery(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Eatery aliceCopy = new EateryBuilder(TypicalEateries.ALICE).build();
        assertTrue(TypicalEateries.ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(TypicalEateries.ALICE.equals(TypicalEateries.ALICE));

        // null -> returns false
        assertFalse(TypicalEateries.ALICE.equals(null));

        // different type -> returns false
        assertFalse(TypicalEateries.ALICE.equals(5));

        // different eatery -> returns false
        assertFalse(TypicalEateries.ALICE.equals(TypicalEateries.BOB));

        // different name -> returns false
        Eatery editedAlice = new EateryBuilder(TypicalEateries.ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(TypicalEateries.ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EateryBuilder(TypicalEateries.ALICE).withPrice(VALID_PRICE_BOB).build();
        assertFalse(TypicalEateries.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EateryBuilder(TypicalEateries.ALICE).withCuisine(VALID_CUISINE_BOB).build();
        assertFalse(TypicalEateries.ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EateryBuilder(TypicalEateries.ALICE).withLocation(VALID_ADDRESS_BOB).build();
        assertFalse(TypicalEateries.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EateryBuilder(TypicalEateries.ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(TypicalEateries.ALICE.equals(editedAlice));
    }
}
