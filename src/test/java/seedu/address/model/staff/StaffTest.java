package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaff.AMY;
import static seedu.address.testutil.TypicalStaff.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StaffBuilder;

public class StaffTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Staff Staff = new StaffBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> Staff.getTags().remove(0));
    }

    @Test
    public void isSameStaff() {
        // same object -> returns true
        assertTrue(AMY.isSameStaff(AMY));

        // null -> returns false
        assertFalse(AMY.isSameStaff(null));

        // same name, all other attributes different -> returns true
        Staff editedAMY = new StaffBuilder(AMY).withStaffContact(VALID_STAFFCONTACT_BOB).withStaffTitle(VALID_STAFFTITLE_BOB)
                .withStaffDepartment(VALID_STAFFDEPARTMENT_BOB).withStaffInsurance(VALID_STAFFINSURANCE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(AMY.isSameStaff(editedAMY));

        // different name, all other attributes same -> returns false
        editedAMY = new StaffBuilder(AMY).withStaffName(VALID_STAFFNAME_BOB).build();
        assertFalse(AMY.isSameStaff(editedAMY));

        // name differs in case, all other attributes same -> returns false
        Staff editedBob = new StaffBuilder(BOB).withStaffName(VALID_STAFFNAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStaff(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOB).withStaffName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStaff(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff AMYCopy = new StaffBuilder(AMY).build();
        assertTrue(AMY.equals(AMYCopy));

        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different Staff -> returns false
        assertFalse(AMY.equals(BOB));

        // different name -> returns false
        Staff editedAMY = new StaffBuilder(AMY).withStaffName(VALID_NAME_BOB).build();
        assertFalse(AMY.equals(editedAMY));

        // different contact -> returns false
        editedAMY = new StaffBuilder(AMY).withStaffContact(VALID_BUDGET_BOB).build();
        assertFalse(AMY.equals(editedAMY));

        // different title -> returns false
        editedAMY = new StaffBuilder(AMY).withStaffTitle(VALID_STAFFTITLE_BOB).build();
        assertFalse(AMY.equals(editedAMY));

        // different department -> returns false
        editedAMY = new StaffBuilder(AMY).withStaffDepartment(VALID_STAFFDEPARTMENT_BOB).build();
        assertFalse(AMY.equals(editedAMY));

        // different insurance -> returns false
        editedAMY = new StaffBuilder(AMY).withStaffInsurance(VALID_STAFFINSURANCE_BOB).build();
        assertFalse(AMY.equals(editedAMY));

        // different tags -> returns false
        editedAMY = new StaffBuilder(AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(AMY.equals(editedAMY));
    }
}
