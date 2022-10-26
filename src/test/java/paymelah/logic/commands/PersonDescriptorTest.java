package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.logic.commands.CommandTestUtil.DESC_AMY;
import static paymelah.logic.commands.CommandTestUtil.DESC_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static paymelah.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static paymelah.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import org.junit.jupiter.api.Test;

import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.testutil.PersonDescriptorBuilder;

public class PersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        PersonDescriptor descriptorWithSameValues = new PersonDescriptor(DESC_AMY);
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
        PersonDescriptor editedAmy = new PersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new PersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different telegram handle -> returns false
        editedAmy = new PersonDescriptorBuilder(DESC_AMY).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new PersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new PersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
