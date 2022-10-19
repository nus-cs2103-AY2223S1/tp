package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PRACTICE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.PRACTICE;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getTags().remove(0));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(PRESENTATION.isSameEvent(PRESENTATION));

        // null -> returns false
        assertFalse(PRESENTATION.isSameEvent(null));

        // same identity, all other attributes different -> returns true
        Event editedPresentation = new EventBuilder(PRESENTATION)
                .withTags(VALID_TAG_CCA).build();
        assertTrue(PRESENTATION.isSameEvent(editedPresentation));

        // different identities, all other attributes same -> returns false
        editedPresentation = new EventBuilder(PRESENTATION).withTitle(VALID_TITLE_PRACTICE).build();
        assertFalse(PRESENTATION.isSameEvent(editedPresentation));

        // name differs in case, all other attributes same -> returns false
        Event editedPractice = new EventBuilder(PRACTICE).withTitle(VALID_TITLE_PRACTICE.toLowerCase()).build();
        assertFalse(PRACTICE.isSameEvent(editedPractice));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TITLE_PRACTICE + " ";
        editedPractice = new EventBuilder(PRACTICE).withTitle(nameWithTrailingSpaces).build();
        assertFalse(PRACTICE.isSameEvent(editedPractice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event presentationCopy = new EventBuilder(PRESENTATION).build();
        assertTrue(PRESENTATION.equals(presentationCopy));

        // same object -> returns true
        assertTrue(PRESENTATION.equals(PRESENTATION));

        // null -> returns false
        assertFalse(PRESENTATION.equals(null));

        // different type -> returns false
        assertFalse(PRESENTATION.equals(5));

        // different event -> returns false
        assertFalse(PRESENTATION.equals(PRACTICE));

        // different name -> returns false
        Event editedPresentation = new EventBuilder(PRESENTATION).withTitle(VALID_TITLE_PRACTICE).build();
        assertFalse(PRESENTATION.equals(editedPresentation));

        // different start -> returns false
        editedPresentation = new EventBuilder(PRESENTATION).withStartDateTime(VALID_START_PRACTICE).build();
        assertFalse(PRESENTATION.equals(editedPresentation));

        // different end -> returns false
        editedPresentation = new EventBuilder(PRESENTATION).withEndDateTime(VALID_END_PRACTICE).build();
        assertFalse(PRESENTATION.equals(editedPresentation));

        // different tags -> returns false
        editedPresentation = new EventBuilder(PRESENTATION).withTags(VALID_TAG_CCA).build();
        assertFalse(PRESENTATION.equals(editedPresentation));
    }
}
