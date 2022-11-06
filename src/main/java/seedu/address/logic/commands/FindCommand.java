package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentNameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in profNus whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;
    private final StudentNameContainsKeywordsPredicate studentPredicate;

    /**
     * Searches the student and tutor list for matching names.
     *
     * @param predicate
     * @param studentPredicate
     */
    public FindCommand(NameContainsKeywordsPredicate predicate,
                       StudentNameContainsKeywordsPredicate studentPredicate) {
        this.predicate = predicate;
        this.studentPredicate = studentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        model.updateFilteredTutorList(studentPredicate);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(String.format(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredPersonList().size())),
                false, false, false,
                false, false, true, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
