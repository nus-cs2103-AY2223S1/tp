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
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
import static seedu.address.testutil.TypicalProfiles.ALICE;
import static seedu.address.testutil.TypicalProfiles.BENSON;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NuScheduler;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ProfileBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddProfilesToEventCommand.
 */
class AddProfilesToEventCommandTest {

    private Model model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());

    @Test
    public void execute_addNewAttendeesUnfilteredList_success() {
        Event secondEvent = model.getFilteredEventList().get(1);

        EventBuilder eventInList = new EventBuilder(secondEvent);
        Profile validFirstProfile = new ProfileBuilder(ALICE).build();
        Profile validSecondProfile = new ProfileBuilder(BENSON).build();
        Event editedEvent = eventInList.withAttendees(validFirstProfile, validSecondProfile).build();
        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(
                INDEX_SECOND_EVENT, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        String expectedMessage = String.format(AddProfilesToEventCommand.MESSAGE_EDIT_ATTENDEES_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setEventForAttendees(model.getFilteredEventList().get(1), editedEvent);

        assertCommandSuccess(addProfilesToEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addExistingAttendeesUnfilteredList_success() {
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        String expectedMessage = String.format(AddProfilesToEventCommand.MESSAGE_EDIT_ATTENDEES_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(addProfilesToEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validEventIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_SECOND_EVENT);

        Event secondEvent = model.getFilteredEventList().get(0);

        EventBuilder eventInList = new EventBuilder(secondEvent);
        Profile validFirstProfile = new ProfileBuilder(ALICE).build();
        Profile validSecondProfile = new ProfileBuilder(BENSON).build();
        Event editedEvent = eventInList.withAttendees(validFirstProfile, validSecondProfile).build();
        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        String expectedMessage = String.format(AddProfilesToEventCommand.MESSAGE_EDIT_ATTENDEES_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new NuScheduler(model.getNuScheduler()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(addProfilesToEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProfileIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileList().size() + 1);
        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(
                INDEX_FIRST_EVENT, Set.of(outOfBoundIndex));

        assertCommandFailure(addProfilesToEventCommand, model,
                Messages.MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_mixedProfileIndexesUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProfileList().size() + 1);
        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE, outOfBoundIndex));

        assertCommandFailure(addProfilesToEventCommand, model,
                Messages.MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(
                outOfBoundIndex, Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        assertCommandFailure(addProfilesToEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of NUScheduler list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNuScheduler().getEventList().size());

        AddProfilesToEventCommand addProfilesToEventCommand = new AddProfilesToEventCommand(outOfBoundIndex,
                Set.of(INDEX_FIRST_PROFILE, INDEX_SECOND_PROFILE));

        assertCommandFailure(addProfilesToEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddProfilesToEventCommand addFirstProfile = new AddProfilesToEventCommand(
                INDEX_SECOND_EVENT, Set.of(INDEX_FIRST_PROFILE));
        AddProfilesToEventCommand addSecondProfile = new AddProfilesToEventCommand(
                INDEX_SECOND_EVENT, Set.of(INDEX_SECOND_PROFILE));

        // same object -> returns true
        assertTrue(addFirstProfile.equals(addFirstProfile));

        // same values -> returns true
        AddProfilesToEventCommand addFirstProfileCopy = new AddProfilesToEventCommand(
                INDEX_SECOND_EVENT, Set.of(INDEX_FIRST_PROFILE));
        assertTrue(addFirstProfile.equals(addFirstProfileCopy));

        // different types -> returns false
        assertFalse(addFirstProfile.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(addFirstProfile.equals(null));

        // different profile -> returns false
        assertFalse(addFirstProfile.equals(addSecondProfile));

        // different event -> returns false
        AddProfilesToEventCommand addSecondProfileToDifferentEvent = new AddProfilesToEventCommand(
                INDEX_FIRST_EVENT, Set.of(INDEX_SECOND_PROFILE));
        assertFalse(addSecondProfile.equals(addSecondProfileToDifferentEvent));
    }
}
