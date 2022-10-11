package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaff.HOON;
import static seedu.address.testutil.TypicalStaff.IDA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.staff.exceptions.DuplicateStaffException;
import seedu.address.model.staff.exceptions.StaffNotFoundException;
import seedu.address.testutil.StaffBuilder;

public class UniqueStaffListTest {

    private final UniqueStaffList uniqueStaffList = new UniqueStaffList();

    @Test
    public void contains_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.contains(null));
    }

    @Test
    public void contains_staffNotInList_returnsFalse() {
        assertFalse(uniqueStaffList.contains(HOON));
    }

    @Test
    public void contains_staffInList_returnsTrue() {
        uniqueStaffList.add(HOON);
        assertTrue(uniqueStaffList.contains(HOON));
    }

    @Test
    public void contains_staffWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStaffList.add(HOON);
        Staff editedHoon = new StaffBuilder(HOON).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStaffList.contains(editedHoon));
    }

    @Test
    public void add_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.add(null));
    }

    @Test
    public void add_duplicateStaff_throwsDuplicateStaffException() {
        uniqueStaffList.add(HOON);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.add(HOON));
    }

    @Test
    public void setStaff_nullTargetStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(null, HOON));
    }

    @Test
    public void setStaff_nullEditedStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(HOON, null));
    }

    @Test
    public void setStaff_targetStaffNotInList_throwsStaffNotFoundException() {
        assertThrows(StaffNotFoundException.class, () -> uniqueStaffList.setStaff(HOON, HOON));
    }

    @Test
    public void setStaff_editedStaffIsSameStaff_success() {
        uniqueStaffList.add(HOON);
        uniqueStaffList.setStaff(HOON, HOON);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(HOON);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasSameIdentity_success() {
        uniqueStaffList.add(HOON);
        Staff editedHoon = new StaffBuilder(HOON).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStaffList.setStaff(HOON, editedHoon);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(editedHoon);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasDifferentIdentity_success() {
        uniqueStaffList.add(HOON);
        uniqueStaffList.setStaff(HOON, IDA);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(IDA);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasNonUniqueIdentity_throwsDuplicateStaffException() {
        uniqueStaffList.add(HOON);
        uniqueStaffList.add(IDA);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.setStaff(HOON, IDA));
    }

    @Test
    public void remove_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.remove(null));
    }

    @Test
    public void remove_staffDoesNotExist_throwsStaffNotFoundException() {
        assertThrows(StaffNotFoundException.class, () -> uniqueStaffList.remove(HOON));
    }

    @Test
    public void remove_existingStaff_removesStaff() {
        uniqueStaffList.add(HOON);
        uniqueStaffList.remove(HOON);
        UniqueStaffList expecteduniqueStaffList = new UniqueStaffList();
        assertEquals(expecteduniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_nulluniqueStaffList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((UniqueStaffList) null));
    }

    @Test
    public void setStaffs_uniqueStaffList_replacesOwnListWithProvideduniqueStaffList() {
        uniqueStaffList.add(HOON);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(IDA);
        uniqueStaffList.setStaffs(expectedUniqueStaffList);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((List<Staff>) null));
    }

    @Test
    public void setStaffs_list_replacesOwnListWithProvidedList() {
        uniqueStaffList.add(HOON);
        List<Staff> staffList = Collections.singletonList(IDA);
        uniqueStaffList.setStaffs(staffList);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(IDA);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_listWithDuplicateStaffs_throwsDuplicateStaffException() {
        List<Staff> listWithDuplicateStaffs = Arrays.asList(HOON, HOON);
        assertThrows(DuplicateStaffException.class, () -> uniqueStaffList.setStaffs(listWithDuplicateStaffs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStaffList.asUnmodifiableObservableList().remove(0));
    }
}
