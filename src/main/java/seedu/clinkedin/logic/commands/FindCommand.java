package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.person.DetailsContainKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose details contain any of the argument or tagged keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose details contain any of "
            + "the specified prefixed or un-prefixed keywords (case-insensitive) and displays them as a list "
            + "with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Example: " + COMMAND_WORD + " n/alex p/87654321 t/friends t/owesMoney";

    final DetailsContainKeywordsPredicate predicate;

    public FindCommand(DetailsContainKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
