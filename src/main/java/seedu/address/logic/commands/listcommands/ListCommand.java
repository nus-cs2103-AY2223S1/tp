package seedu.address.logic.commands.listcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.model.Model;

/**
 * Lists all items to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "List all the Buyer/Supplier/Deliverer/Order/Pet.\n"
            + "Example: list all/buyer/supplier/deliverer/order/pet";

    /**
     * Removes the predicates on all the lists.
     */
    public void updateFilteredList(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_ALL_BUYERS);
        model.updateFilteredSupplierList(Model.PREDICATE_SHOW_ALL_SUPPLIERS);
        model.updateFilteredDelivererList(Model.PREDICATE_SHOW_ALL_DELIVERERS);
        model.updateFilteredPetList(Model.PREDICATE_SHOW_ALL_PETS);
        model.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);
    }

}
