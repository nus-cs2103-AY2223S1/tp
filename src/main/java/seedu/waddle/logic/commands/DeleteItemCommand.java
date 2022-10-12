package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;


/**
 * Adds an item to an itinerary.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed item list. "
            + "Parameters: "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ITINERARY_SUCCESS = "Deleted item: %1$s";

    private final Index targetIndex;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     */
    public DeleteItemCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();

        // if not at wish stage, throw exception and change to wish stage
        /*
        if (!stageManager.isCurrentStage(Stages.WISH)) {
            return new CommandResult(MESSAGE_WRONG_STAGE);
        }

        stageManager.setHomeStage();
         */
        Itinerary itinerary = stageManager.getSelectedItinerary();

        if (targetIndex.getZeroBased() >= itinerary.getItemSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }
        Item itemToDelete = itinerary.removeItem(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DELETE_ITINERARY_SUCCESS, itemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItemCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteItemCommand) other).targetIndex));
    }
}
