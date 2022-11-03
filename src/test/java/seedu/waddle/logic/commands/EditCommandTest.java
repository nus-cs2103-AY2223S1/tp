package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_TEST;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_SECOND_ITINERARY;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.EditItineraryDescriptorBuilder;
import seedu.waddle.testutil.ItineraryBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Itinerary editedItinerary = new ItineraryBuilder().build();
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(editedItinerary).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITINERARY, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new Waddle(model.getWaddle()), new UserPrefs());
        expectedModel.setItinerary(model.getFilteredItineraryList().get(0), editedItinerary);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItinerary = Index.fromOneBased(model.getFilteredItineraryList().size());
        Itinerary lastItinerary = model.getFilteredItineraryList().get(indexLastItinerary.getZeroBased());

        ItineraryBuilder itineraryInList = new ItineraryBuilder(lastItinerary);
        Itinerary editedItinerary = itineraryInList.withName(VALID_ITINERARY_DESC_TEST).withCountry(VALID_COUNTRY_WINTER)
                .withPeople(VALID_PEOPLE_SUMMER).build();

        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withName(VALID_ITINERARY_DESC_TEST)
                .withCountry(VALID_COUNTRY_WINTER).withPeople(VALID_PEOPLE_SUMMER).build();
        EditCommand editCommand = new EditCommand(indexLastItinerary, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new Waddle(model.getWaddle()), new UserPrefs());
        expectedModel.setItinerary(lastItinerary, editedItinerary);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITINERARY, new EditItineraryDescriptor());
        Itinerary editedItinerary = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new Waddle(model.getWaddle()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);

        Itinerary itineraryInFilteredList = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());
        Itinerary editedItinerary = new ItineraryBuilder(itineraryInFilteredList).withName(VALID_ITINERARY_DESC_TEST).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITINERARY,
                new EditItineraryDescriptorBuilder().withName(VALID_ITINERARY_DESC_TEST).build());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITINERARY_SUCCESS, editedItinerary);

        Model expectedModel = new ModelManager(new Waddle(model.getWaddle()), new UserPrefs());
        expectedModel.setItinerary(model.getFilteredItineraryList().get(0), editedItinerary);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItineraryUnfilteredList_failure() {
        Itinerary firstItinerary = model.getFilteredItineraryList().get(INDEX_FIRST_ITINERARY.getZeroBased());
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(firstItinerary).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ITINERARY, descriptor);
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITINERARY);
    }

    @Test
    public void execute_duplicateItineraryFilteredList_failure() {
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);

        // edit itinerary in filtered list into a duplicate in Waddle
        Itinerary itineraryInList = model.getWaddle().getItineraryList().get(INDEX_SECOND_ITINERARY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITINERARY,
                new EditItineraryDescriptorBuilder(itineraryInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITINERARY);
    }

    @Test
    public void execute_invalidItineraryIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withName(VALID_ITINERARY_DESC_WINTER).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of itinerary list
     */
    @Test
    public void execute_invalidItineraryIndexFilteredList_failure() {
        showItineraryAtIndex(model, INDEX_FIRST_ITINERARY);
        Index outOfBoundIndex = INDEX_SECOND_ITINERARY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWaddle().getItineraryList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditItineraryDescriptorBuilder().withName(VALID_ITINERARY_DESC_WINTER).build());
        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ITINERARY, DESC_SUMMER);

        // same values -> returns true
        EditItineraryDescriptor copyDescriptor = new EditItineraryDescriptor(DESC_SUMMER);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ITINERARY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ITINERARY, DESC_SUMMER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ITINERARY, DESC_WINTER)));
    }

}
