package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Deletes an Event from the AddressBook based on the index supplied
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Event based on the index number in the Event list displayed\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: "
            + COMMAND_WORD
            + " 3";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %1$s";

    private final Index targetEventIndex;

    /**
     * Constructor for DeleteEventCommand
     * @param targetEventIndex location of Event to be deleted
     */
    public DeleteEventCommand(Index targetEventIndex) {
        this.targetEventIndex = targetEventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> currentEventList = model.getFilteredEventList();

        Integer zeroBasedIndex = targetEventIndex.getZeroBased();

        if (zeroBasedIndex >= currentEventList.size()) {
            throw new CommandException("The event index provided is invalid");
        }

        Event toDelete = currentEventList.get(zeroBasedIndex);
        model.deleteEvent(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof DeleteEventCommand
                && targetEventIndex.equals(((DeleteEventCommand) other).targetEventIndex));
    }

}
