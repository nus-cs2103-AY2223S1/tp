package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.StudentContainsInterestPredicate;

//@@author ElijahS67
/**
 * Lists batchmates that match all interests specified by the user.
 * Keyword matching is case-insensitive.
 */
public class FindInterestCommand extends Command {

    public static final String COMMAND_WORD = "findInt";
    public static final String MESSAGE_USAGE = "Find batchmates with specified interests in this manner: "
            +
            "\nfindInt INTEREST [MORE_INTERESTS]...";

    private final StudentContainsInterestPredicate predicate;

    public FindInterestCommand(StudentContainsInterestPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        int numOfStudents = model.getFilteredStudentList().size();
        if (numOfStudents != 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                            numOfStudents), false, false, true, false);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_ONE_STUDENT_LISTED_OVERVIEW,
                            numOfStudents), false, false, true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInterestCommand // instanceof handles nulls
                && predicate.equals(((FindInterestCommand) other).predicate)); // state check
    }
}
