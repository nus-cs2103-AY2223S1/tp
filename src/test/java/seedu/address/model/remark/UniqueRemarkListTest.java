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
    public void contains_companyNotInList_returnsFalse() {
        assertFalse(uniqueRemarkList.contains(ALICE));
    }

    @Test
    public void contains_companyInList_returnsTrue() {
        uniqueRemarkList.add(ALICE);
        assertTrue(uniqueRemarkList.contains(ALICE));
    }

    @Test
    public void contains_companyWithSameIdentityFieldsInList_returnsTrue() {
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
    public void remove_companyDoesNotExist_throwsRemarkNotFoundException() {
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
    public void setCompanies_nullUniqueRemarkList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.setCompanies((UniqueRemarkList) null));
    }

    @Test
    public void setCompanies_uniqueRemarkList_replacesOwnListWithProvidedUniqueRemarkList() {
        uniqueRemarkList.add(ALICE);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BOB);
        uniqueRemarkList.setCompanies(expectedUniqueRemarkList);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setCompanies_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRemarkList.setCompanies((List<Remark>) null));
    }

    @Test
    public void setCompanies_list_replacesOwnListWithProvidedList() {
        uniqueRemarkList.add(ALICE);
        List<Remark> companyList = Collections.singletonList(BOB);
        uniqueRemarkList.setCompanies(companyList);
        UniqueRemarkList expectedUniqueRemarkList = new UniqueRemarkList();
        expectedUniqueRemarkList.add(BOB);
        assertEquals(expectedUniqueRemarkList, uniqueRemarkList);
    }

    @Test
    public void setCompanies_listWithDuplicateCompanies_throwsDuplicateRemarkException() {
        List<Remark> listWithDuplicateCompanies = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateRemarkException.class, () -> uniqueRemarkList.setCompanies(listWithDuplicateCompanies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueRemarkList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toString_companyList_returnsCorrectRepresentation() {
        UniqueRemarkList companies = new UniqueRemarkList();
        companies.add(ALICE);
        companies.add(BOB);

        RemarkName aliceName = ALICE.getName();
        RemarkName bobName = BOB.getName();
        assertEquals(companies.toString(), "Companies: " + aliceName + ", " + bobName);
    }

    @Test
    public void removeByIndex_removesCorrectRemark_success() {
        UniqueRemarkList companies = new UniqueRemarkList();
        companies.add(ALICE);
        companies.add(BOB);

        Remark expectedDeletedRemark = BOB;
        Remark deletedRemark = companies.removeByIndex(1);
        assertEquals(deletedRemark, expectedDeletedRemark);
    }

    @Test
    public void size_returnsCorrectSize_success() {
        UniqueRemarkList companies = new UniqueRemarkList();
        assertEquals(companies.size(), 0);

        companies.add(ALICE);
        assertEquals(companies.size(), 1);

        companies.add(BOB);
        assertEquals(companies.size(), 2);
    }
}
