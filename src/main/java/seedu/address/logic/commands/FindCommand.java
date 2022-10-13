package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " [b/d/s/p/o]/Bernice Charlotte";

    private final NameContainsKeywordsPredicate<Buyer> bPredicate;

    private final NameContainsKeywordsPredicate<Deliverer> dPredicate;

    private final NameContainsKeywordsPredicate<Supplier> sPredicate;

    private final PersonCategory type;

    /**
     * Constructs a FindCommand, which has three predicates - one
     * for Buyers, one for Deliverers and one for Suppliers.
     * Keyword matching is case insensitive.
     *
     * @param bPredicate A Predicate for Buyers.
     * @param dPredicate A Predicate for Deliverers.
     * @param sPredicate A Predicate for Suppliers.
     * @param type Whether to return a CommandResult relevant to the
     *             Buyer, Deliverer or Supplier.
     * @return FindCommand.
     */
    public FindCommand(NameContainsKeywordsPredicate<Buyer> bPredicate,
                       NameContainsKeywordsPredicate<Deliverer> dPredicate,
                       NameContainsKeywordsPredicate<Supplier> sPredicate, PersonCategory type) {
        this.bPredicate = bPredicate;
        this.dPredicate = dPredicate;
        this.sPredicate = sPredicate;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(bPredicate);
        model.updateFilteredDelivererList(dPredicate);
        model.updateFilteredSupplierList(sPredicate);
        if (type.equals(PersonCategory.BUYER)) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredBuyerList().size()));
        } else if (type.equals(PersonCategory.DELIVERER)) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredDelivererList().size()));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredSupplierList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && bPredicate.equals(((FindCommand) other).bPredicate) // state checck
                && dPredicate.equals(((FindCommand) other).dPredicate)
                && sPredicate.equals(((FindCommand) other).sPredicate)
                && type.equals(((FindCommand) other).type));
    }
}
