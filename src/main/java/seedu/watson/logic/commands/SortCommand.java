package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.watson.model.Model;

/**
 * Sorts all persons by grades.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String ASCENDING_ARGS = "ASC";
    public static final String DESCENDING_ARGS = "DESC";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the student list based on grades of a certain subject in ascending or descending. "
            + "Not Case Sensitive.\n"
            + "Parameters: asc (for ascending) OR desc (for descending) "
            + PREFIX_SUBJECT + "[SUBJECT]\n"
            + "Example: " + COMMAND_WORD + " asc " + PREFIX_SUBJECT + "english";
    public static final String MESSAGE_SUCCESS = "Sorted students by grades";
    private final boolean isInAscending;
    private final String subjectName;

    /**
     * Creates a sort command to sort by grade.
     */
    public SortCommand(boolean isInAscending, String subjectName) {
        this.isInAscending = isInAscending;
        this.subjectName = subjectName;
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
        model.sortListByGrade(isInAscending, subjectName);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
