package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.StuNameContainsKeywordsPredicate;

/**
 * Finds and lists all students in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindStuCommand extends Command {

    public static final String COMMAND_WORD = "findstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final StuNameContainsKeywordsPredicate predicate;

    public FindStuCommand(StuNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        if (model.getFilteredStudentList().size() == 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_NO_SUCH_STUDENT, model.getFilteredStudentList().size()));
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStuCommand // instanceof handles nulls
                && predicate.equals(((FindStuCommand) other).predicate)); // state check
    }
}
