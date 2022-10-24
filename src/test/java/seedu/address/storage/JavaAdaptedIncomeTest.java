package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntry.ALLOWANCE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.tag.Tag;



public class JavaAdaptedIncomeTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_AMOUNT = "1e3";
    private static final String INVALID_DATE = "2022-08-22";
    private static final String INVALID_TAG = "Food";

    private static final String VALID_DESCRIPTION = ALLOWANCE.getDescription().fullDescription;
    private static final String VALID_AMOUNT = ALLOWANCE.getAmount().toString();
    private static final String VALID_DATE = ALLOWANCE.getDate().toString();
    private static final String VALID_TAG = ALLOWANCE.getTag().tagName;


    @Test
    public void toModelType_validExpenditureDetails_returnsExpenditure() throws Exception {
        JsonAdaptedIncome income = new JsonAdaptedIncome(ALLOWANCE);

        assertEquals(ALLOWANCE, income.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(INVALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(null, VALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_DESCRIPTION, INVALID_AMOUNT, VALID_DATE, VALID_TAG);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_DESCRIPTION, null, VALID_DATE, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_DESCRIPTION, VALID_AMOUNT, INVALID_DATE, VALID_TAG);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_DESCRIPTION, VALID_AMOUNT, null, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, INVALID_TAG);
        String expectedMessage = Tag.EXPENDITURE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }
}
