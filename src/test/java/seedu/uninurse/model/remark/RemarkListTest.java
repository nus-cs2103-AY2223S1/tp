package seedu.uninurse.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.remark.exceptions.DuplicateRemarkException;
import seedu.uninurse.model.remark.exceptions.RemarkNotFoundException;
import seedu.uninurse.model.remark.exceptions.UnmodifiedRemarkException;
import seedu.uninurse.testutil.TypicalRemarks;

public class RemarkListTest {
    private final RemarkList remarkList = new RemarkList();

    @Test
    public void add_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> remarkList.add(null));
    }

    @Test
    public void add_duplicateRemark_throwsDuplicateRemarkException() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertThrows(DuplicateRemarkException.class, () -> updatedRemarkList
                .add(TypicalRemarks.REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void delete_emptyList_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> remarkList.delete(0));
    }

    @Test
    public void delete_invalidIndex_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> remarkList.delete(-1));
    }

    @Test
    public void delete_indexOutOfbounds_throwsRemarkNotFoundException() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertThrows(RemarkNotFoundException.class, () -> updatedRemarkList.delete(1));
    }

    @Test
    public void edit_emptyList_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> remarkList
                .edit(1, TypicalRemarks.REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void edit_invalidIndex_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> remarkList
                .edit(-1, TypicalRemarks.REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void edit_indexOutOfbounds_throwsRemarkNotFoundException() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertThrows(RemarkNotFoundException.class, () -> updatedRemarkList
                .edit(1, TypicalRemarks.REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void edit_sameRemark_throwsUnmodifiedRemarkException() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertThrows(UnmodifiedRemarkException.class, () -> updatedRemarkList
                .edit(0, TypicalRemarks.REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void edit_differentRemark_success() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        updatedRemarkList = updatedRemarkList.edit(0, TypicalRemarks.REMARK_WHEELCHAIR);

        RemarkList expectedRemarkList = new RemarkList();
        expectedRemarkList = expectedRemarkList.add(TypicalRemarks.REMARK_WHEELCHAIR);
        assertEquals(updatedRemarkList, expectedRemarkList);
    }

    @Test
    public void get_emptyList_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> remarkList.get(0));
    }

    @Test
    public void get_invalidIndex_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> remarkList.get(-1));
    }

    @Test
    public void get_indexOutOfbounds_throwsRemarkNotFoundException() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertThrows(RemarkNotFoundException.class, () -> updatedRemarkList.get(1));
    }

    @Test
    public void get_validIndex_success() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertEquals(updatedRemarkList.get(0), TypicalRemarks.REMARK_MEDICAL_ALLERGY);
    }

    @Test
    public void size_emptyList_returnsZero() {
        assertEquals(remarkList.size(), 0);
    }

    @Test
    public void size_nonEmptyList_returnsNonZero() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertNotEquals(updatedRemarkList.size(), 0);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(remarkList.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_returnsFalse() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertFalse(updatedRemarkList.isEmpty());
    }

    @Test
    public void getInternalList_modifyList_throwsUnsupportedOperationException() {
        RemarkList updatedRemarkList = remarkList.add(TypicalRemarks.REMARK_MEDICAL_ALLERGY);
        assertThrows(UnsupportedOperationException.class, ()
            -> updatedRemarkList.getInternalList().remove(0));
    }
}
