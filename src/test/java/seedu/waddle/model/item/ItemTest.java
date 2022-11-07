package seedu.waddle.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SKINNY;
import static seedu.waddle.testutil.TypicalItems.getArt;
import static seedu.waddle.testutil.TypicalItems.getShopping;
import static seedu.waddle.testutil.TypicalItems.getSkinny;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.Text;
import seedu.waddle.testutil.ItemBuilder;

public class ItemTest {

    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(getShopping().isSameItem(getShopping()));

        // null -> returns false
        assertFalse(getShopping().isSameItem(null));

        // same name, all other attributes different -> returns true
        Item editedShopping = new ItemBuilder(getShopping())
                .withPriority(VALID_PRIORITY_SKINNY).withCost(VALID_COST_SKINNY)
                .withDuration(VALID_DURATION_SKINNY).build();
        assertTrue(getShopping().isSameItem(editedShopping));

        // different name, all other attributes same -> returns false
        editedShopping = new ItemBuilder(getShopping()).withDesc(VALID_ITEM_DESC_SKINNY).build();
        assertFalse(getShopping().isSameItem(editedShopping));

        // name differs in case, all other attributes same -> returns false
        Item editedSkinny = new ItemBuilder(getSkinny()).withDesc(VALID_ITEM_DESC_SKINNY
                .toLowerCase()).build();
        assertFalse(getSkinny().isSameItem(editedSkinny));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_ITEM_DESC_SKINNY + " ";
        editedSkinny = new ItemBuilder(getSkinny()).withDesc(nameWithTrailingSpaces).build();
        assertFalse(getSkinny().isSameItem(editedSkinny));
    }

    @Test
    public void getTimeString_notPlanned() {
        String expectedString = "Time: (Not planned)";
        String actualString = new ItemBuilder().build().getTimeString(Text.INDENT_NONE);
        assertEquals(expectedString, actualString);
    }

    @Test
    public void getTimeString_planned() {
        // middle of the day
        Item plannedItem = new ItemBuilder().withDesc("planned item").build();
        plannedItem.setStartTime(LocalTime.NOON);
        String expectedString = "Time: 12:00 - 13:00";
        String actualString = plannedItem.getTimeString(Text.INDENT_NONE);
        assertEquals(expectedString, actualString);

        // start at midnight
        Item plannedItem2 = new ItemBuilder().withDesc("planned item2").build();
        plannedItem2.setStartTime(LocalTime.MIDNIGHT);
        expectedString = "Time: 00:00 - 01:00";
        actualString = plannedItem2.getTimeString(Text.INDENT_NONE);
        assertEquals(expectedString, actualString);

        // end at midnight
        Item plannedItem3 = new ItemBuilder().withDesc("planned item3").build();
        plannedItem3.setStartTime(LocalTime.parse("23:00"));
        expectedString = "Time: 23:00 - 00:00 (next day)";
        actualString = plannedItem3.getTimeString(Text.INDENT_NONE);
        assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_correctOutput() {
        String expectedString = "Airport" + System.lineSeparator()
                + "    ★★★★★" + System.lineSeparator()
                + "    Cost $100.00" + System.lineSeparator()
                + "    Duration 60 mins" + System.lineSeparator()
                + "    Time: (Not planned)";
        String actualString = new ItemBuilder().build().toString();
        assertEquals(expectedString, actualString);
    }

    @Test
    public void equals() {
        Item shopping = getShopping();
        // same values -> returns true, test does not work
        Item shoppingCopy = new ItemBuilder(getShopping()).build();
        assertTrue(shopping.equals(shoppingCopy));

        // same object -> returns true
        assertTrue(shopping.equals(shopping));

        // null -> returns false
        assertFalse(shopping.equals(null));

        // different type -> returns false
        assertFalse(shopping.equals(5));

        // different item -> returns false
        assertFalse(shopping.equals(getSkinny()));

        // different name -> returns false
        Item editedShopping = new ItemBuilder(getShopping()).withDesc(VALID_ITEM_DESC_SKINNY).build();
        assertFalse(shopping.equals(editedShopping));

        // different duration -> returns false
        editedShopping = new ItemBuilder(getShopping()).withDuration(VALID_DURATION_SKINNY).build();
        assertFalse(shopping.equals(editedShopping));

        // different priority -> returns false
        editedShopping = new ItemBuilder(getShopping()).withPriority(VALID_PRIORITY_SKINNY).build();
        assertFalse(shopping.equals(editedShopping));

        // different cost -> returns false
        editedShopping = new ItemBuilder(getShopping()).withCost(VALID_COST_SKINNY).build();
        assertFalse(shopping.equals(editedShopping));
    }
}
