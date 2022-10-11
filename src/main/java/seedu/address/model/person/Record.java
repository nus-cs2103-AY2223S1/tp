package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a single record in the record list of a Person object.
 */
public class Record {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public static final String MESSAGE_CONSTRAINTS = "Record date should be in the following format: "
            + "DD-MM-YYYY HHmm";
    /* Data Fields */
    public final String record;
    private final LocalDateTime recordDate;


    /**
     * Constructs a record.
     *
     * @param recordDate Date that the record was made.
     * @param record     Contents of the record.
     */
    public Record(String recordDate, String record) {
        requireNonNull(recordDate);
        requireNonNull(record);
        checkArgument(isValidDate(recordDate), MESSAGE_INVALID_DATE_FORMAT);
        this.recordDate = LocalDateTime.parse(recordDate, DATE_FORMAT);
        // record field not checked since any record input can be valid.
        this.record = record;
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
     * Record date getter.
     *
     * @return The record date.
     */
    public String getRecordDate() {
        return recordDate.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return recordDate.format(DATE_FORMAT) + ": " + record;
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
