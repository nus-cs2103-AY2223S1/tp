package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindSupplierCommand extends FindCommand {

    /**
     * Constructs a FindSupplierCommand, which has three predicates.
     * Keyword matching is case insensitive.
     *
     * @param sPredicate A Predicate for Suppliers.
     * @return FindBuyerCommand.
     */
    public FindSupplierCommand(Predicate<Supplier> sPredicate) {
        super(null, null, sPredicate, PersonCategory.SUPPLIER);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredSupplierList(getSupplierPredicate());
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredSupplierList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSupplierCommand // instanceof handles nulls
                && getSupplierPredicate().equals(((FindSupplierCommand) other).getSupplierPredicate()));
    }
}
