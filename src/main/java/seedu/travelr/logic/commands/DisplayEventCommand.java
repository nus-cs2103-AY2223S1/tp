package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;


/**
 * Represents the DisplayEventCommand
 */
public class DisplayEventCommand extends Command {

    public static final String COMMAND_WORD = "display-e";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the event identified by the index number used in the displayed events list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_EVENT_SUCCESS = "Display Event: %1$s \n"
            + "at specified index of displayed events list";

    private final Index targetIndex;

    /**
     * @param targetIndex
     */
    public DisplayEventCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        Event eventToDisplay = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DISPLAY_EVENT_SUCCESS, eventToDisplay));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DisplayEventCommand // instanceof handles nulls
                && targetIndex.equals(((DisplayEventCommand) other).targetIndex)); // state check
    }
}
