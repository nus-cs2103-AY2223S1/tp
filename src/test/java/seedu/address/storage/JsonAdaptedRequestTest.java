package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalOrders;

public class JsonAdaptedRequestTest {
    private static final int INVALID_AGE = -1;
    private static final String INVALID_COLOR = "@qu@m&rine";
    private static final String INVALID_COLOR_PATTERN = "p0lkad0t$";
    private static final String INVALID_SPECIES = "c&pyb&r&";

    private static final int VALID_AGE = 1;
    private static final String VALID_COLOR = "white";
    private static final String VALID_COLOR_PATTERN = "random";
    private static final String VALID_SPECIES = "chinchilla";

    @Test
    public void toModelType_validRequestDetails_returnsRequest() throws Exception {
        JsonAdaptedRequest request = new JsonAdaptedRequest(TypicalOrders.ORDER_1.getRequest());
        assertEquals(TypicalOrders.ORDER_1.getRequest(), request.toModelType());
    }

    /*
    @Test
    public void toModelType_invalidRequestCategory_throwsIllegalValueException() {
        JsonAdaptedRequest buyer = new JsonAdaptedRequest(INVALID_AGE, INVALID_COLOR, INVALID_COLOR_PATTERN,
                VALID_SPECIES);
        String expectedMessage = Request.MESSAGE_USAGE;
        assertThrows(IllegalValueException.class, expectedMessage, Request::toModelType);
    }

     */
}
