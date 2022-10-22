package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemark.ALICE;
import static seedu.address.testutil.TypicalRemark.BOB;

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
        assertFalse(uniqueRemarkList.contains(ALICE));
    }

    @Test
    public void contains_remarkInList_returnsTrue() {
        uniqueRemarkList.add(ALICE);
        assertTrue(uniqueRemarkList.contains(ALICE));
    }

    @Test
    public void contains_remarkWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRemarkList.add(ALICE);
        Remark editedAlice = new RemarkBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueRemarkList.contains(editedAlice));
    }

    @Test
    public void add_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.add(null));
    }

    @Test
    public void add_duplicateRemark_throwsDuplicateRemarkException() {
        uniqueRemarkList.add(ALICE);
        assertThrows(DuplicateRemarkException.class, () -> uniqueRemarkList.add(ALICE));
    }

    @Test
    public void setRemark_nullTargetRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.replaceRemark(null, ALICE));
    }

    @Test
    public void setRemark_nullEditedRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.replaceRemark(ALICE, null));
    }

    @Test
    public void setRemark_targetRemarkNotInList_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> uniqueRemarkList.replaceRemark(ALICE, ALICE));
    }

    @Test
    public void setRemark_editedRemarkIsSameRemark_success() {
        uniqueRemarkList.add(ALICE);
        uniqueRemarkList.replaceRemark(ALICE, ALICE);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(ALICE);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemark_editedRemarkHasSameIdentity_success() {
        uniqueRemarkList.add(ALICE);
        Remark editedAlice = new RemarkBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueRemarkList.replaceRemark(ALICE, editedAlice);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(editedAlice);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemark_editedRemarkHasDifferentIdentity_success() {
        uniqueRemarkList.add(ALICE);
        uniqueRemarkList.replaceRemark(ALICE, BOB);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BOB);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemark_editedRemarkHasNonUniqueIdentity_throwsDuplicateRemarkException() {
        uniqueRemarkList.add(ALICE);
        uniqueRemarkList.add(BOB);
        assertThrows(DuplicateRemarkException.class, () -> uniqueRemarkList.replaceRemark(ALICE, BOB));
    }

    @Test
    public void remove_nullRemark_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.remove(null));
    }

    @Test
    public void remove_remarkDoesNotExist_throwsRemarkNotFoundException() {
        assertThrows(RemarkNotFoundException.class, () -> uniqueRemarkList.remove(ALICE));
    }

    @Test
    public void remove_existingRemark_removesRemark() {
        uniqueRemarkList.add(ALICE);
        uniqueRemarkList.remove(ALICE);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemarks_nullUniqueRemarkList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.setRemarks((UniqueRemarkList) null));
    }

    @Test
    public void setRemarks_uniqueRemarkList_replacesOwnListWithProvidedUniqueRemarkList() {
        uniqueRemarkList.add(ALICE);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BOB);
        uniqueRemarkList.setRemarks(expectedUniqueRemarkList);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemarks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.setRemarks((List<Remark>) null));
    }

    @Test
    public void setRemarks_list_replacesOwnListWithProvidedList() {
        uniqueRemarkList.add(ALICE);
        List<Remark> remarkList = Collections.singletonList(BOB);
        uniqueRemarkList.setRemarks(remarkList);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BOB);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setRemarks_listWithDuplicateRemarks_throwsDuplicateRemarkException() {
        List<Remark> listWithDuplicateRemarks = Arrays.asList(ALICE, ALICE);
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
        remarks.add(ALICE);
        remarks.add(BOB);

        RemarkName aliceName = ALICE.getName();
        RemarkName bobName = BOB.getName();
        assertEquals(remarks.toString(), "Remarks: " + aliceName + ", " + bobName);
    }

    @Test
    public void removeByIndex_removesCorrectRemark_success() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        remarks.add(ALICE);
        remarks.add(BOB);

        Remark expectedDeletedRemark = BOB;
        Remark deletedRemark = remarks.removeByIndex(1);
        assertEquals(deletedRemark, expectedDeletedRemark);
    }

    @Test
    public void size_returnsCorrectSize_success() {
        UniqueRemarkList remarks = new UniqueRemarkList();
        assertEquals(remarks.size(), 0);

        remarks.add(ALICE);
        assertEquals(remarks.size(), 1);

        remarks.add(BOB);
        assertEquals(remarks.size(), 2);
    }
}
