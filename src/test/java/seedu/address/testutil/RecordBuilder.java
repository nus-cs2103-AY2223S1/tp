package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Record objects.
 */
public class RecordBuilder {

    public static final String DEFAULT_RECORD_DATE = "02-02-2022 1222";
    public static final String DEFAULT_RECORD_DATA = "Covid-19";
    public static final String DEFAULT_MEDICATION = Medication.MESSAGE_NO_MEDICATION_GIVEN;

    private LocalDateTime recordDate;
    private String recordData;
    private Set<Medication> medications;

    /**
     * Creates a {@code RecordBuilder} with the default details.
     */
    public RecordBuilder() {
        recordDate = LocalDateTime.parse(DEFAULT_RECORD_DATE, Record.DATE_FORMAT);
        recordData = DEFAULT_RECORD_DATA;
        medications = new HashSet<>();
        medications.add(Medication.of(DEFAULT_MEDICATION));
    }

    /**
     * Initializes the RecordBuilder with the data of {@code recordToCopy}.
     */
    public RecordBuilder(Record recordToCopy) {
        recordDate = recordToCopy.getRecordDate();
        recordData = recordToCopy.getRecordData();
        medications = new HashSet<>(recordToCopy.getMedications());
    }

    /**
     * Sets the {@code RecordDate} of the {@code Record} that we are building.
     */
    public RecordBuilder withRecordDate(String date) {
        LocalDateTime formattedDate = LocalDateTime.parse(date, Record.DATE_FORMAT);
        this.recordDate = formattedDate;
        return this;
    }

    /**
     * Parses the {@code medications} into a {@code Set<Medication>} and set it to the {@code Record}
     * that we are building.
     */
    public RecordBuilder withMedications(String ... medications) {
        this.medications = SampleDataUtil.getMedicationSet(medications);
        return this;
    }



    /**
     * Sets the {@code RecordData} of the {@code Record} that we are building.
     */
    public RecordBuilder withRecordData(String data) {
        this.recordData = data;
        return this;
    }


    public Record build() {
        return new Record(recordDate, recordData, medications);
    }

}
