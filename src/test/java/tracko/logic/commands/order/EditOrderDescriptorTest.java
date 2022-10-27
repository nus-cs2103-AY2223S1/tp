package tracko.logic.commands.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tracko.logic.commands.CommandTestUtil;
import tracko.logic.commands.order.EditOrderCommand.EditOrderDescriptor;
import tracko.testutil.EditOrderDescriptorBuilder;

public class EditOrderDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditOrderDescriptor descriptorWithSameValues = new EditOrderDescriptor(CommandTestUtil.DESC_AMY);
        Assertions.assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        Assertions.assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        EditOrderDescriptor editedAmy = new EditOrderDescriptorBuilder(
                CommandTestUtil.DESC_AMY).withName(CommandTestUtil.VALID_NAME_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditOrderDescriptorBuilder(
                CommandTestUtil.DESC_AMY).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditOrderDescriptorBuilder(
                CommandTestUtil.DESC_AMY).withEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditOrderDescriptorBuilder(
                CommandTestUtil.DESC_AMY).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different item list -> returns false
        editedAmy = new EditOrderDescriptorBuilder(
                CommandTestUtil.DESC_AMY).withSecondItemList().build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }
}
