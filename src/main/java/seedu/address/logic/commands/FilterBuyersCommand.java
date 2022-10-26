package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.buyer.AbstractFilterBuyerPredicate;

/**
 * Filters and lists all buyers in the buyer list that either have a price range that accepts the given price,
 * characteristics that match the given characteristics list, or have the given tag.
 * Keyword matching is case-insensitive.
 */
public class FilterBuyersCommand extends Command {

    public static final String COMMAND_WORD = "filterbuyers";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all buyers in the database who"
            + " either have a price range that accepts the given price, characteristics that match the given"
            + " characteristics list, or has the given tag. You must filter by exactly one criteria at a time.\n"
            + "Parameters: [" + PREFIX_PRICE + " PRICE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS] "
            // TODO change this from tag to priority after andre's refactor
            + "[" + PREFIX_PRIORITY + " <HIGH/LOW>]\n"
            + "Example: " + COMMAND_WORD + " -c bright; sunny";

    private final AbstractFilterBuyerPredicate predicate;

    public FilterBuyersCommand(AbstractFilterBuyerPredicate predicate) {
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
                || (other instanceof FilterBuyersCommand // instanceof handles nulls
                && predicate.equals(((FilterBuyersCommand) other).predicate)); // state check
    }
}
