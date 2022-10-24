package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %1$s";

    public static final String MESSAGE_SUCCESS_EMPTY = "Listed";

    public static final String MESSAGE_USAGE = "List all the Buyer/Supplier/Deliverer/Order/Pet.\n"
            + "Example: list, list all/buyer/supplier/deliverer/order/pet";

    public static final String LIST_EMPTY = "EMPTY";
    public static final String LIST_BUYER = "BUYER";
    public static final String LIST_SUPPLIER = "SUPPLIER";
    public static final String LIST_DELIVERER = "DELIVERER";
    public static final String LIST_ORDER = "ORDER";
    public static final String LIST_PET = "PET";
    public static final String LIST_ALL = "ALL";

    private final String listType;
    public ListCommand(String listType) {
        this.listType = listType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_ALL_BUYERS);
        model.updateFilteredSupplierList(Model.PREDICATE_SHOW_ALL_SUPPLIERS);
        model.updateFilteredDelivererList(Model.PREDICATE_SHOW_ALL_DELIVERERS);
        model.updateFilteredPetList(Model.PREDICATE_SHOW_ALL_PETS);
        model.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);
        if (this.listType == ListCommand.LIST_EMPTY) {
            return new CommandResult(MESSAGE_SUCCESS_EMPTY, false, false, true, listType);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, listType), false, false, true, listType);
    }

}
