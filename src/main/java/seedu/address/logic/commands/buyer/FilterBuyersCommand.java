package seedu.address.logic.commands.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUZZY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;


/**
 * Filters and lists all buyers in the buyer list that have a price range that accepts the given price,
 * contains all the characteristics in the given characteristics list, or have the given the priority.
 * More than one filtering criteria can be accepted, buyers matching any or all of the given criteria can be returned
 * based on whether the user passes in the 'PREFIX_MATCH_ALL' flag.
 * Keyword matching is case-insensitive.
 */
public class FilterBuyersCommand extends Command {

    public static final String COMMAND_WORD = "filterbuyers";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all buyers in the database who"
            + " either has a price range that accepts the given price, contains all characteristics in the given"
            + " characteristics list, or has the given priority. You can pass in more than one criteria at a time.\n"
            + " Pass in " + PREFIX_FUZZY + " for fuzzy filtering, i.e. filtered buyers will only need to satisfy"
            + " at least one of the conditions specified. \n"
            + "Parameters: [" + PREFIX_PRICE + " PRICE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS] "
            + "[" + PREFIX_PRIORITY + " <HIGH/LOW>]"
            + "[" + PREFIX_FUZZY + "]\n"
            + "Example: " + COMMAND_WORD + " -c bright; sunny";

    private final Predicate<Buyer> predicate;

    public FilterBuyersCommand(Predicate<Buyer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BUYERS_LISTED_OVERVIEW, model.getFilteredBuyerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterBuyersCommand // instanceof handles nulls
                && predicate.equals(((FilterBuyersCommand) other).predicate)); // state check
    }
}
