package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DAY_NUMBER;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalTime;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.DayNumber;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Plans an item in the itinerary wish list.
 */
public class PlanCommand extends Command {

    public static final String COMMAND_WORD = "plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules an item identified "
            + "by the index number used in the item list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DAY_NUMBER + "DAY NUMBER] "
            + "[" + PREFIX_START_TIME + "START TIME] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DAY_NUMBER + "1 "
            + PREFIX_START_TIME + "12:00 ";

    public static final String MESSAGE_SUCCESS = "Item scheduled: %1$s";
    public static final String MESSAGE_INVALID_DAY_NUMBER = "The day you have selected does not exist";

    private final Index itemIndex;
    private final DayNumber dayNumber;
    private final LocalTime startTime;

    /**
     * Creates a PlanCommand to add the specified {@code Item}
     */
    public PlanCommand(Index itemIndex, DayNumber dayNumber, LocalTime startTime) {
        requireNonNull(itemIndex);
        requireNonNull(dayNumber);
        requireNonNull(startTime);

        this.itemIndex = itemIndex;
        this.dayNumber = dayNumber;
        this.startTime = startTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();

        Itinerary itinerary = stageManager.getSelectedItinerary();

        Item plannedItem;

        try {
            plannedItem = itinerary.planItem(itemIndex, dayNumber, startTime);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_DAY_NUMBER);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, plannedItem.getDescription()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanCommand // instanceof handles nulls
                && itemIndex.equals(((PlanCommand) other).itemIndex)
                && dayNumber == ((PlanCommand) other).dayNumber
                && startTime.equals(((PlanCommand) other).startTime));
    }
}
