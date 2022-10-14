package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Entry}.
 */
public class JsonAdaptedEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    protected final String description;
    protected final String amount;
    protected final String date;
    protected final String tagged;


    /**
     * Constructs a {@code JsonAdaptedEntry} with the given person details.
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
        amount = source.getAmount().amount;
        date = source.getDate().date;
        tagged = source.getTag().tagName;
    }

    /**
     * Checks the JsonEntry for validity
     * @throws IllegalValueException if the json entry is not valid
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
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
    }
    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public Entry toModelType() throws IllegalValueException {
        checkIsValidJsonEntry();
        // final List<Tag> personTags = new ArrayList<>();
        // for (JsonAdaptedTag tag : tagged) {
        //     personTags.add(tag.toModelType());
        // }
        final Description modelDescription = new Description(description);
        final Amount modelAmount = new Amount(amount);
        final Date modelDate = new Date(date);
        final Tag modelTag = new Tag(null, tagged);
        return new Entry(modelDescription, modelDate, modelAmount, modelTag);
    }
}
