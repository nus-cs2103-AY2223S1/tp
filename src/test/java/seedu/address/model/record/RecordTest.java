package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_MEDICATION;
import static seedu.address.model.record.Record.DATE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.RECORD1;
import static seedu.address.testutil.TypicalRecords.RECORD2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecordBuilder;

public class RecordTest {

    @Test
    public void isValidDateFormat() {
        assertThrows(NullPointerException.class, () -> Record.isValidDateFormat(null)); // null date

        // invalid dates
        assertFalse(Record.isValidDateFormat("")); // empty string
        assertFalse(Record.isValidDateFormat(" ")); // spaces only
        assertFalse(Record.isValidDateFormat("02022002 2222")); // incorrect date format
        assertFalse(Record.isValidDateFormat("11.11.2001 1100")); // incorrect date format
        assertFalse(Record.isValidDateFormat("2009.09.21 0000")); // incorrect date format
        assertFalse(Record.isValidDateFormat("10 Jul 2022 1000")); // incorrect date format
        assertFalse(Record.isValidDateFormat("12 12 2007 1230")); // incorrect date format
        assertFalse(Record.isValidDateFormat("2004-11-11 1300")); // incorrect date format
        assertFalse(Record.isValidDateFormat("01-01-2001")); // missing time
        assertFalse(Record.isValidDateFormat("01-01-2001 3000")); // invalid time

        // valid dates
        assertTrue(Record.isValidDateFormat("01-01-2001 1200"));
        assertTrue(Record.isValidDateFormat("12-02-1927 1230"));
        assertTrue(Record.isValidDateFormat("31-12-2003 1300"));
    }

    @Test
    void isFutureDate() {
        assertTrue(Record.isFutureDate(LocalDateTime.now().plusYears(10).format(DATE_FORMAT)));
        assertTrue(Record.isFutureDate(LocalDateTime.now().plusDays(1).format(DATE_FORMAT)));
        assertTrue(Record.isFutureDate(LocalDateTime.now().plusHours(1).format(DATE_FORMAT)));

        assertFalse(Record.isFutureDate(LocalDateTime.now().minusYears(6).format(DATE_FORMAT)));
        assertFalse(Record.isFutureDate(LocalDateTime.now().minusDays(1).format(DATE_FORMAT)));
        assertFalse(Record.isFutureDate(LocalDateTime.now().minusHours(1).format(DATE_FORMAT)));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Record recordCopy = new RecordBuilder(RECORD1).build();
        assertTrue(RECORD1.equals(recordCopy));

        // same object -> returns true
        assertTrue(RECORD1.equals(RECORD1));

        // null -> returns false
        assertFalse(RECORD1.equals(null));

        // different type -> returns false
        assertFalse(RECORD1.equals(5));

        // different record -> returns false
        assertFalse(RECORD1.equals(RECORD2));

        // different date -> returns false
        Record editedRecord = new RecordBuilder(RECORD1).withRecordDate(VALID_RECORD_DATE).build();
        assertFalse(RECORD1.equals(editedRecord));

        // different data -> returns false
        editedRecord = new RecordBuilder(RECORD1).withRecordData(VALID_RECORD_DATA).build();
        assertFalse(RECORD1.equals(editedRecord));

        // different medications -> returns false
        editedRecord = new RecordBuilder(RECORD1).withMedications(VALID_RECORD_MEDICATION).build();
        assertFalse(RECORD1.equals(editedRecord));
    }
}
