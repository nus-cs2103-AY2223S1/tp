package seedu.address.logic.commands.findcommands;

import java.util.function.Predicate;

import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindDelivererCommand extends FindCommand {

    /**
     * Constructs a FindDelivererCommand, which has three predicates.
     * Keyword matching is case insensitive.
     *
     * @param bPredicate A Predicate for Buyers.
     * @param dPredicate A Predicate for Deliverers.
     * @param sPredicate A Predicate for Suppliers.
     * @return FindDelivererCommand.
     */
    public FindDelivererCommand(Predicate<Buyer> bPredicate, Predicate<Deliverer> dPredicate,
                               Predicate<Supplier> sPredicate) {
        super(bPredicate, dPredicate, sPredicate, PersonCategory.DELIVERER);
    }
}
