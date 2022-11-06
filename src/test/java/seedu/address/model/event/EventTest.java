package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TITLE_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PURPOSE_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_CHOCOLATE;
import static seedu.address.testutil.TypicalEvents.SHOE_SALE;
import static seedu.address.testutil.TypicalEvents.SLIPPER_SALE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(SHOE_SALE.isSameEvent(SHOE_SALE));

        // null -> returns false
        assertFalse(SHOE_SALE.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedShoeSale = new EventBuilder(SHOE_SALE).withStartTime(VALID_START_TIME_CHOCOLATE)
                .withDate(VALID_DATE_CHOCOLATE).withPurpose(VALID_PURPOSE_CHOCOLATE).build();
        assertTrue(SHOE_SALE.isSameEvent(editedShoeSale));

        // different name, all other attributes same -> returns false
        editedShoeSale = new EventBuilder(SHOE_SALE).withEventTitle(VALID_EVENT_TITLE_CHOCOLATE).build();
        assertFalse(SHOE_SALE.isSameEvent(editedShoeSale));

        // name differs in case, all other attributes same -> returns false
        Event editedSlipperSale = new EventBuilder(SLIPPER_SALE).withEventTitle(VALID_EVENT_TITLE_CHOCOLATE).build();
        assertFalse(SLIPPER_SALE.isSameEvent(editedSlipperSale));

        // name has trailing spaces, all other attributes same -> returns false
        String eventTitleWithTrailingSpaces = VALID_EVENT_TITLE_CHOCOLATE + " ";
        editedSlipperSale = new EventBuilder(SLIPPER_SALE).withEventTitle(eventTitleWithTrailingSpaces).build();
        assertFalse(SLIPPER_SALE.isSameEvent(editedSlipperSale));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event shoeSaleCopy = new EventBuilder(SHOE_SALE).build();
        assertTrue(SHOE_SALE.equals(shoeSaleCopy));

        // same object -> returns true
        assertTrue(SHOE_SALE.equals(SHOE_SALE));

        // null -> returns false
        assertFalse(SHOE_SALE.equals(null));

        // different type -> returns false
        assertFalse(SHOE_SALE.equals("10000"));

        // different person -> returns false
        assertFalse(SHOE_SALE.equals(SLIPPER_SALE));

        // different eventTitle -> returns false
        Event editedShoeSale = new EventBuilder(SHOE_SALE).withEventTitle(VALID_EVENT_TITLE_CHOCOLATE).build();
        assertFalse(SHOE_SALE.equals(editedShoeSale));

        // different date -> returns false
        editedShoeSale = new EventBuilder(SHOE_SALE).withDate(VALID_DATE_CHOCOLATE).build();
        assertFalse(SHOE_SALE.equals(editedShoeSale));

        // different startTime -> returns false
        editedShoeSale = new EventBuilder(SHOE_SALE).withStartTime(VALID_START_TIME_CHOCOLATE).build();
        assertFalse(SHOE_SALE.equals(editedShoeSale));

        // different purpose -> returns false
        editedShoeSale = new EventBuilder(SHOE_SALE).withPurpose(VALID_PURPOSE_CHOCOLATE).build();
        assertFalse(SHOE_SALE.equals(editedShoeSale));
    }
}
