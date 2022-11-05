package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHOCOLATE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TITLE_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SOCKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PURPOSE_SOCKS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_CHOCOLATE);
        assertTrue(DESC_CHOCOLATE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHOCOLATE.equals(DESC_CHOCOLATE));

        // null -> returns false
        assertFalse(DESC_CHOCOLATE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHOCOLATE.equals(1505));

        // different values -> returns false
        assertFalse(DESC_CHOCOLATE.equals(DESC_SOCKS));

        // different eventTitle -> returns false
        EditEventDescriptor editedChocolate = new EditEventDescriptorBuilder(DESC_CHOCOLATE)
                .withEventTitle(VALID_EVENT_TITLE_SOCKS).build();
        assertFalse(DESC_CHOCOLATE.equals(editedChocolate));

        // different date -> returns false
        editedChocolate = new EditEventDescriptorBuilder(DESC_CHOCOLATE).withDate(VALID_DATE_SOCKS).build();
        assertFalse(DESC_CHOCOLATE.equals(editedChocolate));

        // different startTime -> returns false
        editedChocolate = new EditEventDescriptorBuilder(DESC_CHOCOLATE).withStartTime(VALID_START_TIME_SOCKS).build();
        assertFalse(DESC_CHOCOLATE.equals(editedChocolate));

        // different Purpose -> returns false
        editedChocolate = new EditEventDescriptorBuilder(DESC_CHOCOLATE).withPurpose(VALID_PURPOSE_SOCKS).build();
        assertFalse(DESC_CHOCOLATE.equals(editedChocolate));

    }
}
