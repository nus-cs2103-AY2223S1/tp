package seedu.pennywise.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pennywise.commons.exceptions.IllegalValueException;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Expenditure;
import seedu.pennywise.model.entry.Tag;

/**
 * Jackson-friendly version of {@link Expenditure}.
 */
public class JsonAdaptedExpenditure extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedExpenditure} with the given {@code Entry} details.
     */
    protected static EntryType type = new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE);
    @JsonCreator
    public JsonAdaptedExpenditure(@JsonProperty("description") String description,
                                  @JsonProperty("amount") String amount,
                                  @JsonProperty("date") String date,
                                  @JsonProperty("tagged") String tagged) {
        super(description, amount, date, tagged);
    }

    public JsonAdaptedExpenditure(Entry source) {
        super(source);
    }

    /**
     * Checks the {@code JsonEntry} for validity
     * @throws IllegalValueException if the JSON entry is not valid
     */
    @Override
    public void checkIsValidJsonEntry() throws IllegalValueException {
        super.checkIsValidJsonEntry();
        if (!Tag.isValidTagName(JsonAdaptedExpenditure.type, tagged)) {
            throw new IllegalValueException(Tag.EXPENDITURE_CONSTRAINTS);
        }
    }

    @Override
    public Expenditure toModelType() throws IllegalValueException {
        checkIsValidJsonEntry();
        final Description modelDescription = new Description(description);
        final Amount modelAmount = new Amount(amount);
        final Date modelDate = new Date(date);
        final Tag modelTag = new Tag(JsonAdaptedExpenditure.type, tagged);
        return new Expenditure(modelDescription, modelDate, modelAmount, modelTag);
    }
}
