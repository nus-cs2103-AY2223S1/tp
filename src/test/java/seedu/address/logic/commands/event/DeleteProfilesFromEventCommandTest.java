package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROFILE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NuScheduler;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteProfilesFromEventCommand.
 */
public class DeleteProfilesFromEventCommandTest {

    private Model model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());

    @Test
    public void execute_deleteExistingAttendeesUnfilteredList_success() {
        Event firstEvent = model.getFilteredEventList().get(0);

        EventBuilder eventInList = new EventBuilder(firstEvent);
        Event editedEvent = eventInList.withAttendees().build();
        DeleteProfilesFromEventCommand deleteProfilesFromEventCommand = new DeleteProfilesFromEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        String expectedMessage = String.format(
                DeleteProfilesFromEventCommand.MESSAGE_EDIT_ATTENDEES_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(deleteProfilesFromEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProfileIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().get(0).numberOfAttendees() + 1);
        DeleteProfilesFromEventCommand deleteProfilesFromEventCommand = new DeleteProfilesFromEventCommand(
                INDEX_FIRST_EVENT, Set.of(outOfBoundIndex));

        assertCommandFailure(deleteProfilesFromEventCommand, model,
                Messages.MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixedProfileIndexesUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(3);
        DeleteProfilesFromEventCommand deleteProfilesFromEventCommand = new DeleteProfilesFromEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE, outOfBoundIndex));

        assertCommandFailure(deleteProfilesFromEventCommand, model,
                Messages.MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DeleteProfilesFromEventCommand deleteProfilesFromEventCommand = new DeleteProfilesFromEventCommand(
                outOfBoundIndex, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        assertCommandFailure(deleteProfilesFromEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of NUScheduler list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNuScheduler().getEventList().size());

        DeleteProfilesFromEventCommand deleteProfilesFromEventCommand = new DeleteProfilesFromEventCommand(
                outOfBoundIndex, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        assertCommandFailure(deleteProfilesFromEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteProfilesFromEventCommand deleteFirstAttendee = new DeleteProfilesFromEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE));
        DeleteProfilesFromEventCommand deleteSecondAttendee = new DeleteProfilesFromEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_SECOND_PROFILE));

        // same object -> returns true
        assertTrue(deleteFirstAttendee.equals(deleteFirstAttendee));

        // same values -> returns true
        DeleteProfilesFromEventCommand deleteFirstAttendeeCopy = new DeleteProfilesFromEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE));
        assertTrue(deleteFirstAttendee.equals(deleteFirstAttendeeCopy));

        // different types -> returns false
        assertFalse(deleteFirstAttendee.equals(new AddProfilesToEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE))));

        // null -> returns false
        assertFalse(deleteFirstAttendee.equals(null));

        // different profile -> returns false
        assertFalse(deleteFirstAttendee.equals(deleteSecondAttendee));

        // different event -> returns false
        DeleteProfilesFromEventCommand deleteSecondAttendeeFromDifferentEvent = new DeleteProfilesFromEventCommand(
                INDEX_THIRD_EVENT, Set.of(INDEX_SECOND_PROFILE));
        assertFalse(deleteSecondAttendee.equals(deleteSecondAttendeeFromDifferentEvent));
    }
}
