package seedu.travelr.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.model.AddressBook;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.testutil.EditTripDescriptorBuilder;
import seedu.travelr.testutil.TripBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.commands.CommandTestUtil.DESC_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.DESC_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_EATING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.travelr.logic.commands.CommandTestUtil.showTripAtIndex;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_FIRST_TRIP;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_SECOND_TRIP;
import static seedu.travelr.testutil.TypicalTrips.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Trip editedTrip = new TripBuilder().build();
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder(editedTrip).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TRIP, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TRIP_SUCCESS, editedTrip);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTrip(model.getFilteredTripList().get(0), editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTrip = Index.fromOneBased(model.getFilteredTripList().size());
        Trip lastTrip = model.getFilteredTripList().get(indexLastTrip.getZeroBased());

        TripBuilder tripInList = new TripBuilder(lastTrip);
        Trip editedTrip = tripInList.withTitle(VALID_TITLE_ANTARCTICA)
                .withEvents(VALID_EVENT_EATING).build();

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withTitle(VALID_TITLE_ANTARCTICA)
                .withEvents(VALID_EVENT_EATING).build();
        EditCommand editCommand = new EditCommand(indexLastTrip, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TRIP_SUCCESS, editedTrip);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTrip(lastTrip, editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TRIP, new EditTripDescriptor());
        Trip editedTrip = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TRIP_SUCCESS, editedTrip);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        Trip tripInFilteredList = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        Trip editedTrip = new TripBuilder(tripInFilteredList).withTitle(VALID_TITLE_ANTARCTICA).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TRIP,
                new EditTripDescriptorBuilder().withTitle(VALID_TITLE_ANTARCTICA).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TRIP_SUCCESS, editedTrip);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTrip(model.getFilteredTripList().get(0), editedTrip);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTripUnfilteredList_failure() {
        Trip firstTrip = model.getFilteredTripList().get(INDEX_FIRST_TRIP.getZeroBased());
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder(firstTrip).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TRIP, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TRIP);
    }

    @Test
    public void execute_duplicateTripFilteredList_failure() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);

        // edit trip in filtered list into a duplicate in address book
        Trip tripInList = model.getAddressBook().getTripList().get(INDEX_SECOND_TRIP.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TRIP,
                new EditTripDescriptorBuilder(tripInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TRIP);
    }

    @Test
    public void execute_invalidTripIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTripList().size() + 1);
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withTitle(VALID_TITLE_ANTARCTICA).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTripIndexFilteredList_failure() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);
        Index outOfBoundIndex = INDEX_SECOND_TRIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTripList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTripDescriptorBuilder().withTitle(VALID_TITLE_ANTARCTICA).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TRIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TRIP, DESC_GERMANY);

        // same values -> returns true
        EditTripDescriptor copyDescriptor = new EditTripDescriptor(DESC_GERMANY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TRIP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TRIP, DESC_GERMANY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TRIP, DESC_ANTARCTICA)));
    }

}
