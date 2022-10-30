package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Request;
import seedu.address.model.person.PersonCategory;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.BENSON;

public class JsonAdaptedRequestTest {
    private final int INVALID_AGE = -1;
    private final String INVALID_COLOR = "@qu@m&rine";
    private final String INVALID_COLOR_PATTERN = "p0lkad0t$";
    private final String INVALID_SPECIES = "c&pyb&r&";

    private final int VALID_AGE = 1;
    private final String VALID_COLOR = "white";
    private final String VALID_COLOR_PATTERN = "random";
    private final String VALID_SPECIES = "chinchilla";

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
