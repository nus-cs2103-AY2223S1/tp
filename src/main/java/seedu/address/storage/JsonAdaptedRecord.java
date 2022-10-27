package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedRecord {

    private final String record;
    private final String recordDate;
    private final List<JsonAdaptedMedication> medicationList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given {@code Record details}.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("recordDate") String recordDate,
                             @JsonProperty("record") String record,
                             @JsonProperty("medicationList") List<JsonAdaptedMedication> medicationList) {
        this.record = record;
        this.recordDate = recordDate;

        if (medicationList != null) {
            this.medicationList.addAll(medicationList);
        }
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        this.record = source.record;
        this.recordDate = source.getRecordDate().format(Record.DATE_FORMAT);

        medicationList.addAll(source.getMedications().stream()
                .map(JsonAdaptedMedication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Record toModelType() throws IllegalValueException {
        final List<Medication> recordMedications = new ArrayList<>();
        for (JsonAdaptedMedication med : medicationList) {
            recordMedications.add(med.toModelType());
        }

        if (!Record.isValidDateFormat(recordDate)) {
            throw new IllegalValueException(Record.MESSAGE_INVALID_DATE_FORMAT);
        }

        if (Record.isFutureDate(recordDate)) {
            throw new IllegalValueException(Record.MESSAGE_FUTURE_DATE);
        }

        if (!Record.isValidRecordData(record)) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT);
        }

        final Set<Medication> modelMedication = new HashSet<>(recordMedications);
        return new Record(LocalDateTime.parse(recordDate, Record.DATE_FORMAT), record, modelMedication);
    }

}
