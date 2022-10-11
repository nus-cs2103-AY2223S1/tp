package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaff.BOB;
import static seedu.address.testutil.TypicalStaff.NEVER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.TypicalStaff;

public class StaffTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Staff staff = new StaffBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> staff.getTags().remove(0));
    }

    @Test
    public void isSameStaff() {
        // same object -> returns true
        assertTrue(NEVER.isSameStaff(NEVER));

        // null -> returns false
        assertFalse(NEVER.isSameStaff(null));

        // same name, all other attributes different -> returns true
        Staff editedNever = new StaffBuilder(NEVER).withStaffContact(VALID_STAFFCONTACT_AMY)
                .withStaffTitle(VALID_STAFFTITLE_AMY)
                .withStaffDepartment(VALID_STAFFDEPARTMENT_AMY)
                .withStaffInsurance(VALID_STAFFINSURANCE_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(NEVER.isSameStaff(editedNever));

        // different name, all other attributes same -> returns false
        editedNever = new StaffBuilder(NEVER).withStaffName(VALID_STAFFNAME_BOB).build();
        assertFalse(NEVER.isSameStaff(editedNever));

        // name differs in case, all other attributes same -> returns false
        Staff editedBob = new StaffBuilder(BOB).withStaffName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStaff(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOB).withStaffName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStaff(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff neverCopy = new StaffBuilder(NEVER).build();
        assertTrue(NEVER.equals(neverCopy));

        // same object -> returns true
        assertTrue(NEVER.equals(NEVER));

        // null -> returns false
        assertFalse(NEVER.equals(null));

        // different type -> returns false
        assertFalse(NEVER.equals(5));

        // different staff -> returns false
        assertFalse(NEVER.equals(TypicalStaff.BOB));

        // different name -> returns false
        Staff editedNever = new StaffBuilder(NEVER).withStaffName(VALID_NAME_BOB).build();
        assertFalse(NEVER.equals(editedNever));

        // different contact -> returns false
        editedNever = new StaffBuilder(NEVER).withStaffContact(VALID_STAFFCONTACT_BOB).build();
        assertFalse(NEVER.equals(editedNever));

        // different title -> returns false
        editedNever = new StaffBuilder(NEVER).withStaffTitle(VALID_STAFFTITLE_BOB).build();
        assertFalse(NEVER.equals(editedNever));

        //different department -> returns false;
        editedNever = new StaffBuilder(NEVER).withStaffDepartment(VALID_STAFFDEPARTMENT_BOB).build();
        assertFalse(NEVER.equals(editedNever));

        //different insurance -> returns false;
        editedNever = new StaffBuilder(NEVER).withStaffInsurance(VALID_STAFFINSURANCE_BOB).build();
        assertFalse(NEVER.equals(editedNever));

        // different tags -> returns false
        editedNever = new StaffBuilder(NEVER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(NEVER.equals(editedNever));
    }
}
