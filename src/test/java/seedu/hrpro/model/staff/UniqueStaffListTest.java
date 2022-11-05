package seedu.hrpro.model.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalStaff.STAFF_HOON;
import static seedu.hrpro.testutil.TypicalStaff.STAFF_IDA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hrpro.model.staff.exceptions.DuplicateStaffException;
import seedu.hrpro.model.staff.exceptions.StaffNotFoundException;
import seedu.hrpro.testutil.StaffBuilder;

public class UniqueStaffListTest {

    private final UniqueStaffList uniqueStaffList = new UniqueStaffList();

    @Test
    public void contains_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.contains(null));
    }

    @Test
    public void contains_staffNotInList_returnsFalse() {
        assertFalse(uniqueStaffList.contains(STAFF_HOON));
    }

    @Test
    public void contains_staffInList_returnsTrue() {
        uniqueStaffList.add(STAFF_HOON);
        assertTrue(uniqueStaffList.contains(STAFF_HOON));
    }

    @Test
    public void contains_staffWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStaffList.add(STAFF_HOON);
        Staff editedStaffHoon = new StaffBuilder(STAFF_HOON).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStaffList.contains(editedStaffHoon));
    }

    @Test
    public void add_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.add(null));
    }

    @Test
    public void add_duplicateStaff_throwsDuplicateStaffException() {
        uniqueStaffList.add(STAFF_HOON);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.add(STAFF_HOON));
    }

    @Test
    public void setStaff_nullTargetStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(null, STAFF_HOON));
    }

    @Test
    public void setStaff_nullEditedStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(STAFF_HOON, null));
    }

    @Test
    public void setStaff_targetStaffNotInList_throwsStaffNotFoundException() {
        assertThrows(StaffNotFoundException.class, () -> uniqueStaffList.setStaff(STAFF_HOON, STAFF_HOON));
    }

    @Test
    public void setStaff_editedStaffIsSameStaff_success() {
        uniqueStaffList.add(STAFF_HOON);
        uniqueStaffList.setStaff(STAFF_HOON, STAFF_HOON);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(STAFF_HOON);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasSameIdentity_success() {
        uniqueStaffList.add(STAFF_HOON);
        Staff editedStaffHoon = new StaffBuilder(STAFF_HOON).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStaffList.setStaff(STAFF_HOON, editedStaffHoon);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(editedStaffHoon);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasDifferentIdentity_success() {
        uniqueStaffList.add(STAFF_HOON);
        uniqueStaffList.setStaff(STAFF_HOON, STAFF_IDA);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(STAFF_IDA);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasNonUniqueIdentity_throwsDuplicateStaffException() {
        uniqueStaffList.add(STAFF_HOON);
        uniqueStaffList.add(STAFF_IDA);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.setStaff(STAFF_HOON, STAFF_IDA));
    }

    @Test
    public void remove_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.remove(null));
    }

    @Test
    public void remove_staffDoesNotExist_throwsStaffNotFoundException() {
        assertThrows(StaffNotFoundException.class, () -> uniqueStaffList.remove(STAFF_HOON));
    }

    @Test
    public void remove_existingStaff_removesStaff() {
        uniqueStaffList.add(STAFF_HOON);
        uniqueStaffList.remove(STAFF_HOON);
        UniqueStaffList expecteduniqueStaffList = new UniqueStaffList();
        assertEquals(expecteduniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_nulluniqueStaffList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((UniqueStaffList) null));
    }

    @Test
    public void setStaffs_uniqueStaffList_replacesOwnListWithProvideduniqueStaffList() {
        uniqueStaffList.add(STAFF_HOON);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(STAFF_IDA);
        uniqueStaffList.setStaffs(expectedUniqueStaffList);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((List<Staff>) null));
    }

    @Test
    public void setStaffs_list_replacesOwnListWithProvidedList() {
        uniqueStaffList.add(STAFF_HOON);
        List<Staff> staffList = Collections.singletonList(STAFF_IDA);
        uniqueStaffList.setStaffs(staffList);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(STAFF_IDA);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_listWithDuplicateStaffs_throwsDuplicateStaffException() {
        List<Staff> listWithDuplicateStaffs = Arrays.asList(STAFF_HOON, STAFF_HOON);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.setStaffs(listWithDuplicateStaffs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStaffList.asUnmodifiableObservableList().remove(0));
    }
}
