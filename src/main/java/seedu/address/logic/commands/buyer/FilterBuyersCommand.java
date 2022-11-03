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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all buyers in Cobb that satisfy the"
            + " given price AND contain all the given characteristics AND has the given priority.\n"
            + "Pass in " + PREFIX_FUZZY + " to loosen the filtering criteria, meaning filtered buyers will only need"
            + " to satisfy the given price OR contain at least one given characteristic OR have the given priority.\n"
            + "Parameters: [" + PREFIX_PRICE + " PRICE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS] "
            + "[" + PREFIX_PRIORITY + " PRIORITY <HIGH/NORMAL/LOW>]"
            + "[" + PREFIX_FUZZY + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PRIORITY + " NORMAL";

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
