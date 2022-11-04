package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFF_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFF_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFLEAVE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFTITLE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.hrpro.testutil.EditStaffDescriptorBuilder;

public class EditStaffDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStaffDescriptor descriptorWithSameValues = new EditStaffDescriptor(STAFF_DESC_ANDY);
        assertTrue(STAFF_DESC_ANDY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(STAFF_DESC_ANDY.equals(STAFF_DESC_ANDY));

        // null -> returns false
        assertFalse(STAFF_DESC_ANDY.equals(null));

        // different types -> returns false
        assertFalse(STAFF_DESC_ANDY.equals("Hello"));

        // different values -> returns false
        assertFalse(STAFF_DESC_ANDY.equals(STAFF_DESC_JAY));

        // different name -> returns false
        EditStaffDescriptor editedAndy = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY)
                .withName(VALID_STAFFNAME_JAY).build();
        assertFalse(STAFF_DESC_ANDY.equals(editedAndy));;

        // different contact -> returns false
        editedAndy = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY)
                .withContact(VALID_STAFFCONTACT_JAY).build();
        assertFalse(STAFF_DESC_ANDY.equals(editedAndy));;

        // different department -> returns false
        editedAndy = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY)
                .withDepartment(VALID_STAFFDEPARTMENT_JAY).build();
        assertFalse(STAFF_DESC_ANDY.equals(editedAndy));;

        // different title -> returns false
        editedAndy = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY)
                .withTitle(VALID_STAFFTITLE_JAY).build();
        assertFalse(STAFF_DESC_ANDY.equals(editedAndy));;

        // different leave -> returns false
        editedAndy = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY)
                .withLeave(VALID_STAFFLEAVE_JAY).build();
        assertFalse(STAFF_DESC_ANDY.equals(editedAndy));;

        // different tags -> returns false
        editedAndy = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(STAFF_DESC_ANDY.equals(editedAndy));;
    }
}
