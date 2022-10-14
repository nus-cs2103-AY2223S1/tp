package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaff.STAFF_1;
import static seedu.address.testutil.TypicalStaff.STAFF_JAY;

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
        assertTrue(STAFF_1.isSameStaff(STAFF_1));

        // null -> returns false
        assertFalse(STAFF_1.isSameStaff(null));

        // same name, all other attributes different -> returns true
        Staff editedStaff1 = new StaffBuilder(STAFF_1).withStaffContact(VALID_STAFFCONTACT_ANDY)
                .withStaffTitle(VALID_STAFFTITLE_ANDY)
                .withStaffDepartment(VALID_STAFFDEPARTMENT_ANDY)
                .withStaffInsurance(VALID_STAFFINSURANCE_ANDY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(STAFF_1.isSameStaff(editedStaff1));

        // different name, all other attributes same -> returns false
        editedStaff1 = new StaffBuilder(STAFF_1).withStaffName(VALID_STAFFNAME_JAY).build();
        assertFalse(STAFF_1.isSameStaff(editedStaff1));

        // name differs in case, all other attributes same -> returns false
        Staff editedJay = new StaffBuilder(STAFF_JAY).withStaffName(VALID_STAFFNAME_JAY.toLowerCase()).build();
        assertFalse(STAFF_JAY.isSameStaff(editedJay));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_STAFFNAME_JAY + " ";
        editedJay = new StaffBuilder(STAFF_JAY).withStaffName(nameWithTrailingSpaces).build();
        assertFalse(STAFF_JAY.isSameStaff(editedJay));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff staff1Copy = new StaffBuilder(STAFF_1).build();
        assertTrue(STAFF_1.equals(staff1Copy));

        // same object -> returns true
        assertTrue(STAFF_1.equals(STAFF_1));

        // null -> returns false
        assertFalse(STAFF_1.equals(null));

        // different type -> returns false
        assertFalse(STAFF_1.equals(5));

        // different staff -> returns false
        assertFalse(STAFF_1.equals(TypicalStaff.STAFF_JAY));

        // different name -> returns false
        Staff editedStaff1 = new StaffBuilder(STAFF_1).withStaffName(VALID_STAFFNAME_JAY).build();
        assertFalse(STAFF_1.equals(editedStaff1));

        // different contact -> returns false
        editedStaff1 = new StaffBuilder(STAFF_1).withStaffContact(VALID_STAFFCONTACT_JAY).build();
        assertFalse(STAFF_1.equals(editedStaff1));

        // different title -> returns false
        editedStaff1 = new StaffBuilder(STAFF_1).withStaffTitle(VALID_STAFFTITLE_JAY).build();
        assertFalse(STAFF_1.equals(editedStaff1));

        //different department -> returns false;
        editedStaff1 = new StaffBuilder(STAFF_1).withStaffDepartment(VALID_STAFFDEPARTMENT_JAY).build();
        assertFalse(STAFF_1.equals(editedStaff1));

        //different insurance -> returns false;
        editedStaff1 = new StaffBuilder(STAFF_1).withStaffInsurance(VALID_STAFFINSURANCE_JAY).build();
        assertFalse(STAFF_1.equals(editedStaff1));

        // different tags -> returns false
        editedStaff1 = new StaffBuilder(STAFF_1).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(STAFF_1.equals(editedStaff1));
    }
}
