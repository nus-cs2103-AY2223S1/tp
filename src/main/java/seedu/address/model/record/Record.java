package seedu.address.model.record;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECORD_DATA_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.record.Medication.MESSAGE_NO_MEDICATION_GIVEN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single record in the record list of a Person object.
 */
public class Record implements Comparable<Record> {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Record dates have to be of format dd-MM-yyyy HHmm!"
            + "Please also ensure this is a valid date!";
    public static final String MESSAGE_FUTURE_DATE = "Record dates must not be later than the current date!";
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
     * Returns true if the record date is in the valid format.
     *
     * @param testDate Date to be tested.
     * @return True if the record date is in the valid format.
     */
    public static boolean isValidDateFormat(String testDate) {
        try {
            LocalDateTime.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the record date is after the current date.
     *
     * @param testDate Date to be tested.
     * @return True if the record date is after the current date.
     */
    public static boolean isFutureDate(String testDate) {
        LocalDateTime recordDate = LocalDateTime.parse(testDate, DATE_FORMAT);
        LocalDateTime currDate = LocalDateTime.now();
        return recordDate.isAfter(currDate);
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
     * Record data getter.
     *
     * @return The record data.
     */
    public String getRecordData() {
        return this.record;
    }

    /**
     * Medication Set getter.
     *
     * @return Unmodifiable set of medications.
     */
    public Set<Medication> getMedications() {
        return Collections.unmodifiableSet(medications);
    }

    /**
     * Returns true if both record have the same record data and date.
     * This defines a weaker notion of equality between two record.
     */
    public boolean isSameRecord(Record otherRecord) {
        if (otherRecord == this) {
            return true;
        }

        return otherRecord != null
                && otherRecord.getRecordDate().equals(getRecordDate());
    }

    @Override
    public int compareTo(Record record) {
        return this.recordDate.compareTo(record.recordDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(getRecordDate().format(DATE_FORMAT))
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
                && record.equals((((Record) other).record))
                && medications.equals(((Record) other).medications)); // state check
    }


    /* todo figure out what this should be */
    @Override
    public int hashCode() {
        return record.hashCode();
    }
}
