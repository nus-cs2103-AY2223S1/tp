package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.DESC_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

import org.junit.jupiter.api.Test;

import tuthub.logic.commands.EditCommand.EditTutorDescriptor;
import tuthub.testutil.EditTutorDescriptorBuilder;

public class EditTutorDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTutorDescriptor descriptorWithSameValues = new EditTutorDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditTutorDescriptor editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different module -> returns false
        editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withModule(VALID_MODULE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different year -> returns false
        editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withYear(VALID_YEAR_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditTutorDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
