package seedu.pennyWise.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pennyWise.commons.exceptions.IllegalValueException;
import seedu.pennyWise.model.entry.Amount;
import seedu.pennyWise.model.entry.Date;
import seedu.pennyWise.model.entry.Description;
import seedu.pennyWise.model.entry.Entry;
import seedu.pennyWise.model.entry.EntryType;
import seedu.pennyWise.model.entry.Income;
import seedu.pennyWise.model.entry.Tag;

/**
 * Jackson-friendly version of {@link Income}
 */
public class JsonAdaptedIncome extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedEntry} with the given income details.
     */
    protected static EntryType type = new EntryType("i");

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
