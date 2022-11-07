package seedu.pennywise.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pennywise.commons.exceptions.IllegalValueException;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.Tag;

/**
 * Jackson-friendly version of {@link Entry}.
 */
public abstract class JsonAdaptedEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";
    protected final String description;
    protected final String amount;
    protected final String date;
    protected final String tagged;

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given {@code Entry} details.
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("description") String description, @JsonProperty("amount") String amount,
                            @JsonProperty("date") String date, @JsonProperty("tagged") String tagged) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.tagged = tagged;
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        description = source.getDescription().fullDescription;
        amount = source.getAmount().toString();
        date = source.getDate().toString();
        tagged = source.getTag().getTagName();
    }

    /**
     * Checks the {@code JsonEntry} for validity
     *
     * @throws IllegalValueException if the JSON entry is not valid
     */
    public void checkIsValidJsonEntry() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        if (!Amount.isAmountWithinLimits(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_TOO_LARGE);
        }
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        if (tagged == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }
    }
    /**
     * Converts this Jackson-friendly adapted Entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public abstract Entry toModelType() throws IllegalValueException;
}
