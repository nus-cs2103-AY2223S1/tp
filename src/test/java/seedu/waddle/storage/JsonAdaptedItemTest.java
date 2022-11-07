package seedu.waddle.storage;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.testutil.TypicalItems;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonAdaptedItemTest {
    @Test
    public void toModelType_validItemDetails_returnsItem() throws IllegalValueException {
        JsonAdaptedItem item = new JsonAdaptedItem(TypicalItems.getArt());
        assertEquals(TypicalItems.getArt(), item.toModelType());
    }
}
