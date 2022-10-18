package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_MEDICATION;
import static seedu.address.testutil.TypicalPersons.RECORD1;
import static seedu.address.testutil.TypicalPersons.RECORD2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecordBuilder;



public class RecordTest {

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
