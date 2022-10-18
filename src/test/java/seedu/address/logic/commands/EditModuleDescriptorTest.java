package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_TITLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(DESC_CS2106);
        assertTrue(DESC_CS2106.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2106.equals(DESC_CS2106));

        // null -> returns false
        assertFalse(DESC_CS2106.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2106.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2106.equals(DESC_MA2001));

        // different module code -> returns false
        EditModuleDescriptor editedCs2106 = new EditModuleDescriptorBuilder(DESC_CS2106)
                .withModuleCode(VALID_MA2001_MODULE_CODE).build();
        assertFalse(DESC_CS2106.equals(editedCs2106));

        // different module title -> returns false
        editedCs2106 = new EditModuleDescriptorBuilder(DESC_CS2106)
                .withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
        assertFalse(DESC_CS2106.equals(editedCs2106));
    }
}
