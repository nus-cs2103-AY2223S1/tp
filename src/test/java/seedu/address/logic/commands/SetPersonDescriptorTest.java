package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLACK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEZONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetCommand.SetPersonDescriptor;
import seedu.address.testutil.SetPersonDescriptorBuilder;

public class SetPersonDescriptorTest {

    @Test
    public void equals() {
        // same values returns true
        SetPersonDescriptor descriptorWithSameValues = new SetPersonDescriptor(DESC_AMY);
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
        SetPersonDescriptor editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different role -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withRole(VALID_ROLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different timezone -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withTimezone(VALID_TIMEZONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // missing tags -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withTags().build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // extra tags -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different telegram -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different slack -> returns false
        editedAmy = new SetPersonDescriptorBuilder(DESC_AMY).withSlack(VALID_SLACK_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
