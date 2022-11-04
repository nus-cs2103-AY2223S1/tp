package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.PersonCategory;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindBuyerCommand extends FindCommand {
    /**
     * Constructs a FindBuyerCommand, which has three predicates.
     * Keyword matching is case insensitive.
     *
     * @param bPredicate A Predicate for Buyers.
     * @return FindBuyerCommand.
     */
    public FindBuyerCommand(Predicate<Buyer> bPredicate) {
        super(bPredicate, null, null, PersonCategory.BUYER);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(getBuyerPredicate());
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredBuyerList().size()));
    }
}
