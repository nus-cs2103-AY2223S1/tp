package seedu.waddle.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.waddle.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.testutil.TypicalItems;

public class JsonAdaptedItemTest {
    private static final String INVALID_DESC = "Exchange $$$";
    private static final int INVALID_PRIORITY = 0;
    private static final String INVALID_COST = "-4";
    private static final String INVALID_DURATION = "1 week";
    private static final String INVALID_START_TIME = "12.00";

    private static final String VALID_DESC = TypicalItems.getArt().getDescription().toString();
    private static final int VALID_PRIORITY = TypicalItems.getArt().getPriority().getValue();
    private static final String VALID_COST = TypicalItems.getArt().getCost().toString();
    private static final String VALID_DURATION = TypicalItems.getArt().getDuration().toString();
    private static final String VALID_START_TIME = "10:30";


    @Test
    public void toModelType_validItemDetails_returnsItem() throws IllegalValueException {
        Item itemModel = TypicalItems.getArt();
        JsonAdaptedItem item = new JsonAdaptedItem(itemModel);
        assertEquals(itemModel, item.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(INVALID_DESC, VALID_PRIORITY, VALID_COST,
                VALID_DURATION, VALID_START_TIME);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_PRIORITY, VALID_COST,
                VALID_DURATION, VALID_START_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, INVALID_PRIORITY, VALID_COST,
                VALID_DURATION, VALID_START_TIME);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, null, VALID_COST,
                VALID_DURATION, VALID_START_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, VALID_PRIORITY, INVALID_COST,
                VALID_DURATION, VALID_START_TIME);
        String expectedMessage = Cost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, VALID_PRIORITY, null,
                VALID_DURATION, VALID_START_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, VALID_PRIORITY, VALID_COST,
                INVALID_DURATION, VALID_START_TIME);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, VALID_PRIORITY, VALID_COST,
                null, VALID_START_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsDateTimeParseException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, VALID_PRIORITY, VALID_COST,
                VALID_DURATION, INVALID_START_TIME);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(DateTimeParseException.class, item::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_returnsItem() throws IllegalValueException {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_DESC, VALID_PRIORITY, VALID_COST,
                VALID_DURATION, null);
        Item itemModel = TypicalItems.getArt();
        assertEquals(itemModel, item.toModelType());
    }

}
