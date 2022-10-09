package seedu.address.model.staff;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaff.APPLE;
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
        assertTrue(APPLE.isSameStaff(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameStaff(null));

        // same name, all other attributes different -> returns true
        Staff editedApple = new StaffBuilder(APPLE).withBudget(VALID_BUDGET_BOB).withDeadline(VALID_DEADLINE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSameStaff(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new StaffBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.isSameStaff(editedApple));

        // name differs in case, all other attributes same -> returns false
        Staff editedBob = new StaffBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStaff(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStaff(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff appleCopy = new StaffBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different Staff -> returns false
        assertFalse(APPLE.equals(BOB));

        // different name -> returns false
        Staff editedApple = new StaffBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different budget -> returns false
        editedApple = new StaffBuilder(APPLE).withBudget(VALID_BUDGET_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different deadline -> returns false
        editedApple = new StaffBuilder(APPLE).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new StaffBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
