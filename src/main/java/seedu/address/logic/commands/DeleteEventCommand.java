package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Deletes an Event identified using it's displayed index from the event list of the application.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Event based on the index number in the Event list displayed\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_DELETED_EVENT_SUCCESS = "Deleted Event: %1$s";

    private final Index index;

    /**
     * Creates a DeleteEventCommand.
     * @param targetEventIndex displayed index of Event to be deleted.
     */
    public DeleteEventCommand(Index targetEventIndex) {
        this.index = targetEventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> currentEventList = model.getFilteredEventList();

        Integer zeroBasedIndex = index.getZeroBased();
        if (zeroBasedIndex >= currentEventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event toDelete = currentEventList.get(zeroBasedIndex);
        model.deleteEvent(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETED_EVENT_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof DeleteEventCommand
                && index.equals(((DeleteEventCommand) other).index));
    }

}
