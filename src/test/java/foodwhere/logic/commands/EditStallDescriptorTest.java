package foodwhere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.testutil.EditStallDescriptorBuilder;

public class EditStallDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        SEditCommand.EditStallDescriptor descriptorWithSameValues =
                new SEditCommand.EditStallDescriptor(CommandTestUtil.DESC_AMY);
        assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        SEditCommand.EditStallDescriptor editedAmy =
                new EditStallDescriptorBuilder(CommandTestUtil.DESC_AMY)
                        .withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditStallDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different details -> returns false
        editedAmy = new EditStallDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }
}
