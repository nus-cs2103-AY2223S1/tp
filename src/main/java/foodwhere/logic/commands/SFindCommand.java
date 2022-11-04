package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.commons.core.Messages;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.stall.StallContainsKeywordsPredicate;

/**
 * Finds and lists all stalls in FoodWhere whose name or tags contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SFindCommand extends Command {

    public static final String COMMAND_WORD = "sfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all stalls where either the name or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "[NAME_KEYWORDS]... "
            + CliSyntax.PREFIX_TAG + "[TAG_KEYWORDS]... "
            + "Example: "
            + CliSyntax.PREFIX_NAME + "Chicken "
            + CliSyntax.PREFIX_TAG + "opensDaily ";

    private final StallContainsKeywordsPredicate predicate;

    public SFindCommand(StallContainsKeywordsPredicate predicate) {
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
