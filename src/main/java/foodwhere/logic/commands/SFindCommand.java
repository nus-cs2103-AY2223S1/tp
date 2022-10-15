package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.commons.core.Messages;
import foodwhere.model.Model;
import foodwhere.model.stall.NameContainsKeywordsPredicate;

/**
 * Finds and lists all stalls in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SFindCommand extends Command {

    public static final String COMMAND_WORD = "sfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all stalls whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " John Doe Eatery";

    private final NameContainsKeywordsPredicate predicate;

    public SFindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStallList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STALLS_LISTED_OVERVIEW, model.getFilteredStallList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SFindCommand // instanceof handles nulls
                && predicate.equals(((SFindCommand) other).predicate)); // state check
    }
}
