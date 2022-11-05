package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemark.BAD_BUYER;
import static seedu.address.testutil.TypicalRemark.GOOD_BUYER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.remark.exceptions.DuplicateRemarkException;
import seedu.address.model.remark.exceptions.RemarkNotFoundException;
import seedu.address.testutil.RemarkBuilder;

public class UniqueRemarkListTest {

    private final UniqueRemarkList uniqueRemarkList = new UniqueRemarkList();

    @Test
    public void contains_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.contains(null));
    }

    @Test
    public void contains_remarkNotInList_returnsFalse() {
        assertFalse(uniqueRemarkList.contains(GOOD_BUYER));
    }

    @Test
    public void contains_remarkInList_returnsTrue() {
        uniqueRemarkList.add(GOOD_BUYER);
        assertTrue(uniqueRemarkList.contains(GOOD_BUYER));
    }

    @Test
    public void contains_remarkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRemarkList.add(GOOD_BUYER);
        Remark editedRemark = new RemarkBuilder(GOOD_BUYER).build();
        assertTrue(uniqueRemarkList.contains(editedRemark));
    }

    @Test
    public void add_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.add(null));
    }

    @Test
    public void add_duplicateRemark_throwsDuplicateRemarkException() {
        uniqueRemarkList.add(GOOD_BUYER);
        assertThrows(DuplicateRemarkException.class, () -> uniqueRemarkList.add(GOOD_BUYER));
    }

    @Test
    public void setRemark_nullTargetRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.replaceRemark(null, GOOD_BUYER));
    }

    @Test
    public void setRemark_nullEditedRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.replaceRemark(GOOD_BUYER, null));
    }

    @Test
    public void setRemark_targetRemarkNotInList_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> uniqueRemarkList.replaceRemark(GOOD_BUYER, GOOD_BUYER));
    }

    @Test
    public void setRemark_editedRemarkIsSameRemark_success() {
        uniqueRemarkList.add(GOOD_BUYER);
        uniqueRemarkList.replaceRemark(GOOD_BUYER, GOOD_BUYER);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(GOOD_BUYER);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemark_editedRemarkHasSameIdentity_success() {
        uniqueRemarkList.add(GOOD_BUYER);
        Remark editedRemark = new RemarkBuilder(GOOD_BUYER).build();
        uniqueRemarkList.replaceRemark(GOOD_BUYER, editedRemark);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(editedRemark);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemark_editedRemarkHasDifferentIdentity_success() {
        uniqueRemarkList.add(GOOD_BUYER);
        uniqueRemarkList.replaceRemark(GOOD_BUYER, BAD_BUYER);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BAD_BUYER);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemark_editedRemarkHasNonUniqueIdentity_throwsDuplicateRemarkException() {
        uniqueRemarkList.add(GOOD_BUYER);
        uniqueRemarkList.add(BAD_BUYER);
        assertThrows(DuplicateRemarkException.class, () -> uniqueRemarkList.replaceRemark(GOOD_BUYER, BAD_BUYER));
    }

    @Test
    public void remove_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.remove(null));
    }

    @Test
    public void remove_remarkDoesNotExist_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> uniqueRemarkList.remove(GOOD_BUYER));
    }

    @Test
    public void remove_existingRemark_removesRemark() {
        uniqueRemarkList.add(GOOD_BUYER);
        uniqueRemarkList.remove(GOOD_BUYER);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemarks_nullUniqueRemarkList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.setRemarks((UniqueRemarkList) null));
    }

    @Test
    public void setRemarks_uniqueRemarkList_replacesOwnListWithProvidedUniqueRemarkList() {
        uniqueRemarkList.add(GOOD_BUYER);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BAD_BUYER);
        uniqueRemarkList.setRemarks(expectedUniqueRemarkList);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemarks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.setRemarks((List<Remark>) null));
    }

    @Test
    public void setRemarks_list_replacesOwnListWithProvidedList() {
        uniqueRemarkList.add(GOOD_BUYER);
        List<Remark> remarkList = Collections.singletonList(BAD_BUYER);
        uniqueRemarkList.setRemarks(remarkList);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BAD_BUYER);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemarks_listWithDuplicateRemarks_throwsDuplicateRemarkException() {
        List<Remark> listWithDuplicateRemarks = Arrays.asList(GOOD_BUYER, GOOD_BUYER);
        assertThrows(DuplicateRemarkException.class, () -> uniqueRemarkList.setRemarks(listWithDuplicateRemarks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueRemarkList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toString_remarkList_returnsCorrectRepresentation() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        remarks.add(GOOD_BUYER);
        remarks.add(BAD_BUYER);

        Text goodBuyerText = GOOD_BUYER.getText();
        Text badBuyerText = BAD_BUYER.getText();
        assertEquals(remarks.toString(), "Remarks: " + goodBuyerText + ", " + badBuyerText);
    }

    @Test
    public void removeByIndex_removesCorrectRemark_success() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        remarks.add(GOOD_BUYER);
        remarks.add(BAD_BUYER);

        Remark expectedDeletedRemark = BAD_BUYER;
        Remark deletedRemark = remarks.removeByIndex(1);
        assertEquals(deletedRemark, expectedDeletedRemark);
    }

    @Test
    public void size_returnsCorrectSize_success() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        assertEquals(remarks.size(), 0);

        remarks.add(GOOD_BUYER);
        assertEquals(remarks.size(), 1);

        remarks.add(BAD_BUYER);
        assertEquals(remarks.size(), 2);
    }
}
