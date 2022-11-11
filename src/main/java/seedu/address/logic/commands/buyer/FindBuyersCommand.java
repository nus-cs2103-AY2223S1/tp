package seedu.address.logic.commands.buyer;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.buyer.BuyerNameContainsSubstringPredicate;

/**
 * Finds and lists all buyers in buyer book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBuyersCommand extends Command {

    public static final String COMMAND_WORD = "findbuyers";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all buyers whose names contain the specified string (case insensitive).\n"
            + "Example: " + COMMAND_WORD + " John";

    private final BuyerNameContainsSubstringPredicate predicate;

    public FindBuyersCommand(BuyerNameContainsSubstringPredicate predicate) {
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
                || (other instanceof FindBuyersCommand // instanceof handles nulls
                && predicate.equals(((FindBuyersCommand) other).predicate)); // state check
    }
}
