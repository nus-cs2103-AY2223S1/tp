package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts meetings by date in ascending or descending order
 */
public class SortMeetingsCommand extends Command {

    public static final String COMMAND_WORD = "sortmeetings";
    public static final String ASCENDING_ARGS = "ASC";
    public static final String DESCENDING_ARGS = "DESC";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the meeting list based on date in ascending or descending. Not Case Sensitive.\n"
            + "Parameters: asc (for ascending), desc (for descending)\n"
            + "Example: " + COMMAND_WORD + " asc";
    private final boolean isInAscending;

    public SortMeetingsCommand(boolean isInAscending) {
        this.isInAscending = isInAscending;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortMeetingListByDate(isInAscending);
        return new CommandResult(Messages.MESSAGE_MEETINGS_SORTED);
    }
}
