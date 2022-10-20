package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PRACTICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.EditEventCommand.EditEventDescriptor;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_PRESENTATION);
        assertTrue(DESC_PRESENTATION.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PRESENTATION.equals(DESC_PRESENTATION));

        // null -> returns false
        assertFalse(DESC_PRESENTATION.equals(null));

        // different types -> returns false
        assertFalse(DESC_PRESENTATION.equals(5));

        // different values -> returns false
        assertFalse(DESC_PRESENTATION.equals(DESC_PRACTICE));

        // different title -> returns false
        EditEventDescriptor editedPresentation = new EditEventDescriptorBuilder(DESC_PRESENTATION)
                .withTitle(VALID_TITLE_PRACTICE).build();
        assertFalse(DESC_PRESENTATION.equals(editedPresentation));

        // different start date time -> returns false
        editedPresentation = new EditEventDescriptorBuilder(DESC_PRESENTATION)
                .withStartDateTime(VALID_START_PRACTICE).build();
        assertFalse(DESC_PRESENTATION.equals(editedPresentation));

        // different end date time -> returns false
        editedPresentation = new EditEventDescriptorBuilder(DESC_PRESENTATION)
                .withEndDateTime(VALID_END_PRACTICE).build();
        assertFalse(DESC_PRESENTATION.equals(editedPresentation));


        // different tags -> returns false
        editedPresentation = new EditEventDescriptorBuilder(DESC_PRESENTATION).withTags(VALID_TAG_CCA).build();
        assertFalse(DESC_PRESENTATION.equals(editedPresentation));
    }
}
