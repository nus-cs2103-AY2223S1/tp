package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_SECOND_ITINERARY;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;
import static seedu.waddle.testutil.TypicalMultiIndexes.MULTI_INDEX_FIRST_DAY_FIRST_ITEM;
import static seedu.waddle.testutil.TypicalMultiIndexes.MULTI_INDEX_FIRST_DAY_SECOND_ITEM;
import static seedu.waddle.testutil.TypicalMultiIndexes.MULTI_INDEX_FIRST_UNSCHEDULED_ITEM;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;

public class UnplanCommandTest {

    private final Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnplanCommand(null));
    }

    @Test
    public void execute_validMultiIndexDayList_success() {
        Itinerary validItinerary = getTypicalWaddle().getItineraryList().get(INDEX_SECOND_ITINERARY.getZeroBased());
        UnplanCommand unplanCommand = new UnplanCommand(MULTI_INDEX_FIRST_DAY_FIRST_ITEM);

        Model expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());

        Item validItem = expectedModel.getFilteredItineraryList()
                .get(INDEX_SECOND_ITINERARY.getZeroBased())
                .unplanItem(MULTI_INDEX_FIRST_DAY_FIRST_ITEM);

        String expectedMessage = String.format(UnplanCommand.MESSAGE_SUCCESS, validItem.getDescription());
        StageManager.getInstance().setWishStage(validItinerary);

        assertCommandSuccess(unplanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMultiIndexDayList_throwsCommandException() {
        UnplanCommand unplanCommand = new UnplanCommand(MULTI_INDEX_FIRST_UNSCHEDULED_ITEM);

        assertCommandFailure(unplanCommand, model, UnplanCommand.MESSAGE_INVALID_INDEX_NUMBER);
    }

    @Test
    public void equals() {
        UnplanCommand unplanFirstCommand = new UnplanCommand(MULTI_INDEX_FIRST_DAY_FIRST_ITEM);
        UnplanCommand unplanSecondCommand = new UnplanCommand(MULTI_INDEX_FIRST_DAY_SECOND_ITEM);

        // same object -> returns true
        assertTrue(unplanFirstCommand.equals(unplanFirstCommand));

        // same values -> returns true
        UnplanCommand unplanFirstCommandCopy = new UnplanCommand(MULTI_INDEX_FIRST_DAY_FIRST_ITEM);
        assertTrue(unplanFirstCommand.equals(unplanFirstCommandCopy));

        // different types -> returns false
        assertFalse(unplanFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unplanFirstCommand.equals(null));

        // different multi index -> returns false
        assertFalse(unplanFirstCommand.equals(unplanSecondCommand));
    }
}
