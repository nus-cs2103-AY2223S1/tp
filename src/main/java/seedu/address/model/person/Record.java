package seedu.address.model.person;


import seedu.address.model.tag.Tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.Medication.MESSAGE_NO_MEDICATION_GIVEN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Represents a single record in the record list of a Person object.
 */
public class Record implements Comparable<Record> {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    /* Data Fields */
    public final String record;
    private final LocalDateTime recordDate;
    private final Set<Medication> medications; // optional field

    /**
     * Constructs a record.
     *
     * @param recordDate Date that the record was made.
     * @param record     Contents of the record.
     */
    public Record(LocalDateTime recordDate, String record, Set<Medication> meds) {
        requireAllNonNull(recordDate, record, meds);
        checkArgument(isValidRecordData(record), MESSAGE_INVALID_RECORD_DATA_FORMAT);
        this.recordDate = recordDate;
        this.record = record;
        this.medications = meds;
    }

    /**
     * Constructs a record, with no medication.
     *
     * @param recordDate Date that the record was made.
     * @param record     Contents of the record.
     */
    public Record(LocalDateTime recordDate, String record) {
        requireAllNonNull(recordDate, record);
        checkArgument(isValidRecordData(record), MESSAGE_INVALID_RECORD_DATA_FORMAT);
        this.recordDate = recordDate;
        this.record = record;
        this.medications = new HashSet<Medication>();
        this.medications.add(Medication.of(MESSAGE_NO_MEDICATION_GIVEN));
    }

    /**
     * Returns true if date is valid.
     *
     * @param testDate Date to be tested.
     * @return True if valid.
     */
    public static boolean isValidDate(String testDate) {
        try {
            LocalDateTime.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is valid record data.
     */
    public static boolean isValidRecordData(String test) {
        return !test.isBlank();
    }

    /**
     * Record date getter.
     *
     * @return The record date.
     */
    public LocalDateTime getRecordDate() {
        return this.recordDate;
    }

    /**
     * Medication Set getter.
     *
     * @return Unmodifiable set of medications.
     */
    public Set<Medication> getMedications() {
        return Collections.unmodifiableSet(medications);
    }

    @Override
    public int compareTo(Record record) {
        return this.recordDate.compareTo(record.recordDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getRecordDate().format(DATE_FORMAT))
                .append("; Record: ")
                .append(record);

        Set<Medication> meds = getMedications();
        if (!meds.isEmpty()) {
            builder.append("; Medications: ");
            meds.forEach(med -> builder.append(med + " "));
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Record // instanceof handles nulls
                && recordDate.equals(((Record) other).recordDate)
                && record.equals((((Record) other).record))); // state check
    }

    /* todo figure out what this should be */
    @Override
    public int hashCode() {
        return record.hashCode();
    }
}
