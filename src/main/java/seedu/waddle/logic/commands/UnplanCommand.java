package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Unplans an item in the itinerary day list.
 */
public class UnplanCommand extends Command {

    public static final String COMMAND_WORD = "unplan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unschedules an item identified "
            + "by the index number used in the day list.\n"
            + "Parameters: DAY_INDEX.TASK_INDEX (must exist in the day list) "
            + "Example: " + COMMAND_WORD + " 1.2 ";

    public static final String MESSAGE_SUCCESS = "Item unscheduled: %1$s";
    public static final String MESSAGE_INVALID_INDEX_NUMBER = "The index you have selected does not exist";

    private final MultiIndex multiIndex;

    /**
     * Creates an UnplanCommand to add the specified {@code Item}
     */
    public UnplanCommand(MultiIndex multiIndex) {
        requireNonNull(multiIndex);

        this.multiIndex = multiIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();

        Itinerary itinerary = stageManager.getSelectedItinerary();

        Item unplannedItem;
        try {
            unplannedItem = itinerary.unplanItem(multiIndex);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new CommandException(MESSAGE_INVALID_INDEX_NUMBER);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, unplannedItem.getDescription()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnplanCommand // instanceof handles nulls
                && multiIndex.equals(((UnplanCommand) other).multiIndex));
    }
}
