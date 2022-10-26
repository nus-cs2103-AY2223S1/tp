package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String ASCENDING_ARGS = "ASC";
    public static final String DESCENDING_ARGS = "DESC";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the student list based on grades in ascending or descending. Not Case Sensitive.\n"
            + "Parameters: asc (for ascending), desc (for descending)\n"
            + "Example: " + COMMAND_WORD + " asc";
    public static final String MESSAGE_SUCCESS = "Sorted students by grades";
    private final boolean isInAscending;

    public SortCommand(boolean isInAscending) {
        this.isInAscending = isInAscending;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortListByGrade(isInAscending);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
