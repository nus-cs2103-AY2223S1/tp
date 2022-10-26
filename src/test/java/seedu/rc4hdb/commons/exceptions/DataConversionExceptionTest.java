package seedu.rc4hdb.commons.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DataConversionException}.
 */
public class DataConversionExceptionTest {

    @Test
    public void getMessage_noMessage_returnEmptyString() {
        DataConversionException dce = new DataConversionException(new Exception());
        assertEquals("", dce.getMessage());
    }

    @Test
    public void getMessage_withMessage_returnErrorMessageWithoutException() {
        String dummyString = "Dummy";
        DataConversionException dce = new DataConversionException(new Exception(dummyString));
        assertEquals(dummyString, dce.getMessage());
    }
}
