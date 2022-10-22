package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntry.LUNCH;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.tag.Tag;

public class JsonAdaptedExpenditureTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_AMOUNT = "1e3";
    private static final String INVALID_DATE = "2022-08-22";
    private static final String INVALID_TAG = "Profit";

    private static final String VALID_DESCRIPTION = LUNCH.getDescription().fullDescription;
    private static final String VALID_AMOUNT = LUNCH.getAmount().toString();
    private static final String VALID_DATE = LUNCH.getDate().toString();
    private static final String VALID_TAG = LUNCH.getTag().tagName;


    @Test
    public void toModelType_validExpendioturenDetails_returnsExpenditure() throws Exception {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(LUNCH);

        assertEquals(LUNCH, expenditure.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(INVALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(null, VALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_DESCRIPTION, INVALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_DESCRIPTION, null, VALID_DATE, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_DESCRIPTION, VALID_AMOUNT, INVALID_DATE, VALID_TAG);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_DESCRIPTION, VALID_AMOUNT, null, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, INVALID_TAG);
        String expectedMessage = Tag.EXPENDITURE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure =
                new JsonAdaptedExpenditure(VALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expenditure::toModelType);
    }

}
