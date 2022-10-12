package seedu.travelr.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.testutil.EditTripDescriptorBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.DESC_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.DESC_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;

public class EditTripDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTripDescriptor descriptorWithSameValues = new EditTripDescriptor(DESC_GERMANY);
        assertTrue(DESC_GERMANY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GERMANY.equals(DESC_GERMANY));

        // null -> returns false
        assertFalse(DESC_GERMANY.equals(null));

        // different types -> returns false
        assertFalse(DESC_GERMANY.equals(5));

        // different values -> returns false
        assertFalse(DESC_GERMANY.equals(DESC_ANTARCTICA));

        // different title -> returns false
        EditTripDescriptor editedAmy = new EditTripDescriptorBuilder(DESC_GERMANY).withTitle(VALID_TITLE_ANTARCTICA).build();
        assertFalse(DESC_GERMANY.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditTripDescriptorBuilder(DESC_GERMANY).withDescription(VALID_DESCRIPTION_ANTARCTICA).build();
        assertFalse(DESC_GERMANY.equals(editedAmy));

        // different events -> returns false
        editedAmy = new EditTripDescriptorBuilder(DESC_GERMANY).withEvents(VALID_EVENT_SIGHTSEEING).build();
        assertFalse(DESC_GERMANY.equals(editedAmy));
    }
}
