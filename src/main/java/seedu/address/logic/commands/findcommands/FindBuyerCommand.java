package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.PersonCategory;

/**
 * Finds and lists all Buyers in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindBuyerCommand extends FindCommand {
    /**
     * Constructs a FindBuyerCommand, which has three predicates.
     * Keyword matching is case insensitive.
     *
     * @param bPredicate A Predicate for Buyers.
     */
    public FindBuyerCommand(Predicate<Buyer> bPredicate) {
        super(bPredicate, null, null, PersonCategory.BUYER);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(getBuyerPredicate());
        model.switchToBuyerList();
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredBuyerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBuyerCommand // instanceof handles nulls
                && getBuyerPredicate().equals(((FindBuyerCommand) other).getBuyerPredicate()));
    }
}
