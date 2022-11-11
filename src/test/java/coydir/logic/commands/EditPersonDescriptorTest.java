package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.DESC_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static coydir.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import coydir.logic.commands.EditCommand.EditPersonDescriptor;
import coydir.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different position -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPosition(VALID_POSITION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different department -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withDepartment(VALID_DEPARTMENT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
