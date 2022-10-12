package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Record;
import seedu.address.model.tag.Tag;



/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedRecord {

    private final String record;
    private final String recordDate;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given {@code Record details}.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("recordDate") String recordDate, @JsonProperty("record") String record) {
        this.record = record;
        this.recordDate = recordDate;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        this.record = source.record;
        this.recordDate = source.getRecordDate();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Record toModelType() throws IllegalValueException {
        if (!Record.isValidDate(recordDate)) {
            throw new IllegalValueException(Record.MESSAGE_CONSTRAINTS);
        }
        return new Record(recordDate, record);
    }

}
