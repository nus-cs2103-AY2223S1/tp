package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.Species;
import seedu.address.testutil.TypicalOrders;

public class JsonAdaptedRequestTest {
    private static final String MISSING_FIELD_MESSAGE_FORMAT = "Request's %s field is missing!";

    private static final int INVALID_AGE = -1;

    private static final int VALID_AGE = 1;
    private static final String VALID_COLOR = "white";
    private static final String VALID_COLOR_PATTERN = "random";
    private static final String VALID_SPECIES = "chinchilla";

    @Test
    public void toModelType_validRequestDetails_returnsRequest() throws Exception {
        JsonAdaptedRequest request = new JsonAdaptedRequest(TypicalOrders.ORDER_1.getRequest());
        assertEquals(TypicalOrders.ORDER_1.getRequest(), request.toModelType());
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedRequest request = new JsonAdaptedRequest(INVALID_AGE, VALID_COLOR, VALID_COLOR_PATTERN,
                VALID_SPECIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getName());
        assertThrows(IllegalValueException.class, expectedMessage, request::toModelType);
    }

    @Test
    public void toModelType_nullColor_throwsIllegalValueException() {
        JsonAdaptedRequest request = new JsonAdaptedRequest(VALID_AGE, null, VALID_COLOR_PATTERN, VALID_SPECIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Color.class.getName());
        assertThrows(IllegalValueException.class, expectedMessage, request::toModelType);
    }

    @Test
    public void toModelType_nullColorPattern_throwsIllegalValueException() {
        JsonAdaptedRequest request = new JsonAdaptedRequest(VALID_AGE, VALID_COLOR, null, VALID_SPECIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ColorPattern.class.getName());
        assertThrows(IllegalValueException.class, expectedMessage, request::toModelType);
    }

    @Test
    public void toModelType_nullSpecies_throwsIllegalValueException() {
        JsonAdaptedRequest request = new JsonAdaptedRequest(VALID_AGE, VALID_COLOR, VALID_COLOR_PATTERN, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getName());
        assertThrows(IllegalValueException.class, expectedMessage, request::toModelType);
    }

}
