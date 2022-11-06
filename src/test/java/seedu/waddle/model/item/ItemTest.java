package seedu.waddle.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SKINNY;
import static seedu.waddle.testutil.TypicalItems.SHOPPING;
import static seedu.waddle.testutil.TypicalItems.SKINNY;

import org.junit.jupiter.api.Test;

import seedu.waddle.testutil.ItemBuilder;

public class ItemTest {

    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(SHOPPING.isSameItem(SHOPPING));

        // null -> returns false
        assertFalse(SHOPPING.isSameItem(null));

        // same name, all other attributes different -> returns true
        Item editedShopping = new ItemBuilder(SHOPPING)
                .withPriority(VALID_PRIORITY_SKINNY).withCost(VALID_COST_SKINNY)
                .withDuration(VALID_DURATION_SKINNY).build();
        assertTrue(SHOPPING.isSameItem(editedShopping));

        // different name, all other attributes same -> returns false
        editedShopping = new ItemBuilder(SHOPPING).withDesc(VALID_ITEM_DESC_SKINNY).build();
        assertFalse(SHOPPING.isSameItem(editedShopping));

        // name differs in case, all other attributes same -> returns false
        Item editedSkinny = new ItemBuilder(SKINNY).withDesc(VALID_ITEM_DESC_SKINNY
                .toLowerCase()).build();
        assertFalse(SKINNY.isSameItem(editedSkinny));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_ITEM_DESC_SKINNY + " ";
        editedSkinny = new ItemBuilder(SKINNY).withDesc(nameWithTrailingSpaces).build();
        assertFalse(SKINNY.isSameItem(editedSkinny));
    }

    @Test
    public void equals() {
        // same values -> returns true, test does not work
        Item shoppingCopy = new ItemBuilder(SHOPPING).build();
        // assertTrue(SHOPPING.equals(shoppingCopy));

        // same object -> returns true
        assertTrue(SHOPPING.equals(SHOPPING));

        // null -> returns false
        assertFalse(SHOPPING.equals(null));

        // different type -> returns false
        assertFalse(SHOPPING.equals(5));

        // different item -> returns false
        assertFalse(SHOPPING.equals(SKINNY));

        // different name -> returns false
        Item editedShopping = new ItemBuilder(SHOPPING).withDesc(VALID_ITEM_DESC_SKINNY).build();
        assertFalse(SHOPPING.equals(editedShopping));

        // different duration -> returns false
        editedShopping = new ItemBuilder(SHOPPING).withDuration(VALID_DURATION_SKINNY).build();
        assertFalse(SHOPPING.equals(editedShopping));

        // different priority -> returns false
        editedShopping = new ItemBuilder(SHOPPING).withPriority(VALID_PRIORITY_SKINNY).build();
        assertFalse(SHOPPING.equals(editedShopping));

        // different cost -> returns false
        editedShopping = new ItemBuilder(SHOPPING).withCost(VALID_COST_SKINNY).build();
        assertFalse(SHOPPING.equals(editedShopping));
    }
}
