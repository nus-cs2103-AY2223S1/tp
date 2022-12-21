package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.DetailsContainKeywordsPredicate;

/**
 * Finds and lists all students in mass linkers whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Find batchmates with a keyword in this manner: "
            +
            "\nfind KEYWORD [MORE_KEYWORDS]...";

    private final DetailsContainKeywordsPredicate predicate;

    public FindCommand(DetailsContainKeywordsPredicate predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
