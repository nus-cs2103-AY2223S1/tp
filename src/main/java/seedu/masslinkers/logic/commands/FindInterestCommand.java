package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.StudentContainsInterestPredicate;

/**
 * Lists batchmates that match all interests specified by the user.
 * Keyword matching is case-insensitive.
 */
public class FindInterestCommand extends Command {

    public static final String COMMAND_WORD = "findInt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists batchmates that match all interests specified\n"
            + "Parameters: INTEREST [MORE_INTERESTS]...\n"
            + "Example: " + COMMAND_WORD + " tennis netflix";

    private final StudentContainsInterestPredicate predicate;

    public FindInterestCommand(StudentContainsInterestPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                model.getFilteredStudentList().size()), false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInterestCommand // instanceof handles nulls
                && predicate.equals(((FindInterestCommand) other).predicate)); // state check
    }
}
