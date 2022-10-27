package seedu.pennywise.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pennywise.commons.exceptions.IllegalValueException;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Income;
import seedu.pennywise.model.entry.Tag;

/**
 * Jackson-friendly version of {@link Income}
 */
public class JsonAdaptedIncome extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedIncome} with the given {@code Income} details.
     */
    protected static EntryType type = new EntryType(EntryType.ENTRY_TYPE_INCOME);

    @JsonCreator
    public JsonAdaptedIncome(@JsonProperty("description") String description,
                             @JsonProperty("amount") String amount,
                             @JsonProperty("date") String date,
                             @JsonProperty("tagged") String tagged) {
        super(description, amount, date, tagged);
    }

    public JsonAdaptedIncome(Entry source) {
        super(source);
    }

    /**
     * Checks the JsonEntry for validity
     * @throws IllegalValueException if the json entry is not valid
     */
    @Override
    public void checkIsValidJsonEntry() throws IllegalValueException {
        super.checkIsValidJsonEntry();
        if (!Tag.isValidTagName(JsonAdaptedIncome.type, tagged)) {
            throw new IllegalValueException(Tag.EXPENDITURE_CONSTRAINTS);
        }
    }

    @Override
    public Income toModelType() throws IllegalValueException {
        checkIsValidJsonEntry();

        final Description modelDescription = new Description(description);

        final Amount modelAmount = new Amount(amount);

        final Date modelDate = new Date(date);

        final Tag modelTag = new Tag(JsonAdaptedIncome.type, tagged);
        return new Income(modelDescription, modelDate, modelAmount, modelTag);
    }
}
