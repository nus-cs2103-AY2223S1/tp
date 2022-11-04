package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds buyers, deliverers or suppliers "
            + "who match the specified attribute. "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX/[KEYWORDS]...\n"
            + "For Address, use the prefix 'a' \n"
            + "For Email, use the prefix 'e' \n"
            + "For Location, use the prefix 'l' \n"
            + "For Name, use the prefix 'n' \n"
            + "For Phone, use the prefix 'p' \n"
            + "Example: " + COMMAND_WORD + " n/Bernice";

    private final Predicate<Buyer> buyerPredicate;

    private final Predicate<Deliverer> delivererPredicate;

    private final Predicate<Supplier> supplierPredicate;

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
    public FindCommand(Predicate<Buyer> bPredicate, Predicate<Deliverer> dPredicate,
                       Predicate<Supplier> sPredicate, PersonCategory type) {
        this.buyerPredicate = bPredicate;
        this.delivererPredicate = dPredicate;
        this.supplierPredicate = sPredicate;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(buyerPredicate);
        model.updateFilteredDelivererList(delivererPredicate);
        model.updateFilteredSupplierList(supplierPredicate);
        if (type.equals(PersonCategory.BUYER)) {
            model.switchToBuyerList();
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredCurrList().size()));
        } else if (type.equals(PersonCategory.DELIVERER)) {
            model.switchToDelivererList();
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredCurrList().size()));
        } else {
            model.switchToSupplierList();
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredCurrList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && buyerPredicate.equals(((FindCommand) other).buyerPredicate) // state checck
                && delivererPredicate.equals(((FindCommand) other).delivererPredicate)
                && supplierPredicate.equals(((FindCommand) other).supplierPredicate)
                && type.equals(((FindCommand) other).type));
    }
}
