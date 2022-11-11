package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_MEDICATION;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.RECORD1;
import static seedu.address.testutil.TypicalRecords.RECORD2;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateRecordException;
import seedu.address.model.person.exceptions.RecordNotFoundException;
import seedu.address.testutil.RecordBuilder;

public class RecordListTest {

    private final RecordList recordList = new RecordList();

    @Test
    public void contains_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recordList.contains(null));
    }

    @Test
    public void contains_recordNotInList_returnsFalse() {
        assertFalse(recordList.contains(RECORD1));
    }

    @Test
    public void contains_recordInList_returnsTrue() {
        recordList.add(RECORD1);
        assertTrue(recordList.contains(RECORD1));
    }

    @Test
    public void contains_recordWithSameDateInList_returnsTrue() {
        recordList.add(RECORD1);
        Record editedRecord1 = new RecordBuilder(RECORD1)
                .withRecordData(VALID_RECORD_DATA)
                .withMedications(VALID_RECORD_MEDICATION)
                .build();
        assertTrue(recordList.contains(editedRecord1));
    }

    @Test
    public void add_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recordList.add(null));
    }

    @Test
    public void add_duplicateRecord_throwsDuplicateRecordException() {
        recordList.add(RECORD1);
        assertThrows(DuplicateRecordException.class, () -> recordList.add(RECORD1));
    }

    @Test
    public void set_nullTargetRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recordList.set(null, RECORD1));
    }

    @Test
    public void set_nullEditedRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recordList.set(RECORD1, null));
    }

    @Test
    public void set_targetRecordNotInList_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> recordList.set(RECORD1, RECORD1));
    }

    @Test
    public void set_editedRecordIsSameRecord_success() {
        recordList.add(RECORD1);
        recordList.set(RECORD1, RECORD1);
        RecordList expectedRecordList = new RecordList();
        expectedRecordList.add(RECORD1);
        assertEquals(expectedRecordList, recordList);
    }

    @Test
    public void setRecord_editedRecordHasSameIdentity_success() {
        recordList.add(RECORD1);
        Record editedRecord1 = new RecordBuilder(RECORD1)
                .withRecordDate(VALID_RECORD_DATE)
                .withMedications(VALID_RECORD_MEDICATION)
                .build();
        recordList.set(RECORD1, editedRecord1);
        RecordList expectedRecordList = new RecordList();
        expectedRecordList.add(editedRecord1);
        assertEquals(expectedRecordList, recordList);
    }

    @Test
    public void set_editedRecordHasDifferentIdentity_success() {
        recordList.add(RECORD1);
        recordList.set(RECORD1, RECORD2);
        RecordList expectedRecordList = new RecordList();
        expectedRecordList.add(RECORD2);
        assertEquals(expectedRecordList, recordList);
    }

    @Test
    public void set_editedRecordHasNonUniqueIdentity_throwsDuplicatePersonException() {
        recordList.add(RECORD1);
        recordList.add(RECORD2);
        assertThrows(DuplicateRecordException.class, () -> recordList.set(RECORD1, RECORD2));
    }

    @Test
    public void delete_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recordList.delete(null));
    }

    @Test
    public void delete_recordDoesNotExist_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> recordList.delete(RECORD1));
    }

    @Test
    public void delete_existingRecord_removesRecord() {
        recordList.add(RECORD1);
        recordList.delete(RECORD1);
        RecordList expectedRecordList = new RecordList();
        assertEquals(expectedRecordList, recordList);
    }

    @Test
    public void setRecordList_nullRecordList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recordList.setRecordList((RecordList) null));
    }

    @Test
    public void setRecordList_recordList_replacesOwnListWithProvidedRecordList() {
        recordList.add(RECORD1);
        RecordList expectedRecordList = new RecordList();
        expectedRecordList.add(RECORD2);
        recordList.setRecordList(expectedRecordList);
        assertEquals(expectedRecordList, recordList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> recordList.asUnmodifiableObservableList().remove(0));
    }
}
