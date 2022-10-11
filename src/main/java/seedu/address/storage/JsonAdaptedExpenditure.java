package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Expenditure;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Expenditure}.
 */
public class JsonAdaptedExpenditure extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedEntry} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedExpenditure(@JsonProperty("description") String description,
                                  @JsonProperty("amount") String amount,
                                  @JsonProperty("date") String date,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(description, amount, date, tagged);
    }

    public JsonAdaptedExpenditure(Entry source) {
        super(source);
    }

    @Override
    public Expenditure toModelType() throws IllegalValueException {
        checkIsValidJsonEntry();
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Description modelDescription = new Description(description);
        final Amount modelAmount = new Amount(amount);
        final Date modelDate = new Date(date);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Expenditure(modelDescription, modelDate, modelAmount, modelTags);
    }
}
