package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;

public class DeleteItemCommandTest {
    private Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws CommandException {
        Itinerary itinerary = model.getFilteredItineraryList().get(1);
        MultiIndex targetIndex = new MultiIndex();
        targetIndex.appendZeroBasedIndex(0);
        Item targetItem = itinerary.getItem(targetIndex);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setWishStage(itinerary);
        CommandResult commandResult = new DeleteItemCommand(targetIndex).execute(model);

        assertEquals(String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, targetItem),
                commandResult.getFeedbackToUser());
        assertEquals(false, itinerary.hasItem(targetItem));
    }
}
