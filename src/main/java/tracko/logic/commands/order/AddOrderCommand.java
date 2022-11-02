package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import javafx.util.Pair;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.MultiLevelCommand;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.item.InventoryItem;
import tracko.model.item.Quantity;
import tracko.model.item.exceptions.ItemNotFoundException;
import tracko.model.order.Order;

/**
 * Adds an order to TrackO. The AddOrderCommand is a multi-level command in which the user initiates the order to be
 * added using the command word, before progressively adding an order item and associated quantity to the order to be
 * added.
 */
public class AddOrderCommand extends MultiLevelCommand {

    public static final String COMMAND_WORD = "addo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Initiates an order to add to TrackO. \n"
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_PHONE + "PHONE "
            + CliSyntax.PREFIX_EMAIL + "EMAIL "
            + CliSyntax.PREFIX_ADDRESS + "ADDRESS \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "John Doe "
            + CliSyntax.PREFIX_PHONE + "98765432 "
            + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
            + CliSyntax.PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_USAGE_2 = "To add an item and its quantity to the order being created: \n"
        + CliSyntax.PREFIX_ITEM + "ITEM_NAME "
        + CliSyntax.PREFIX_QUANTITY + "ITEM_QUANTITY \n"
        + "Enter 'done' or 'cancel' to finish or abort the command accordingly.";

    public static final String MESSAGE_ADDED_ITEM = "New item and quantity added:\n%1$s"
            + "\nTo add more items and their quantities, enter:"
            + "\n" + CliSyntax.PREFIX_ITEM + "ITEM_NAME "
            + CliSyntax.PREFIX_QUANTITY + "ITEM_QUANTITY"
            + "\nOtherwise, enter 'done' or 'cancel' to finish or abort the command accordingly.";

    public static final String MESSAGE_INVALID_ITEM = "Item by the name '%1$s' not found in inventory."
            + "\nTo add more items and their quantities, enter:"
            + "\n" + CliSyntax.PREFIX_ITEM + "ITEM_NAME "
            + CliSyntax.PREFIX_QUANTITY + "ITEM_QUANTITY"
            + "\nOtherwise, enter 'done' or 'cancel' to finish or abort the command accordingly.";

    public static final String MESSAGE_INVALID_QUANTITY = "Input quantity '%1$s' is invalid."
            + "\nTo add more items and their quantities, enter:"
            + "\n" + CliSyntax.PREFIX_ITEM + "ITEM_NAME "
            + CliSyntax.PREFIX_QUANTITY + "ITEM_QUANTITY"
            + "\nOtherwise, enter 'done' or 'cancel' to finish or abort the command accordingly.";

    public static final String MESSAGE_EMPTY_ITEM_LIST = "Order's item list cannot be empty!";

    public static final String MESSAGE_COMMAND_ABORTED = "Add order command aborted";

    public static final String MESSAGE_SUCCESS = "New order added:\n%1$s";

    private final Order toAdd;
    private Pair<String, Quantity> itemNameQuantityPairInput;

    /**
     * Creates an AddOrderCommand that is set to await further input from the user.
     * @param order The base order to be created, includes all customer details
     */
    public AddOrderCommand(Order order) {
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.isCancelled) {
            // User has cancelled the command
            return new CommandResult(MESSAGE_COMMAND_ABORTED);
        }

        if (!this.isAwaitingInput()) {
            // User has finished inputting order details
            if (toAdd.getItemList().isEmpty()) {
                // User tried to add an order with empty item list
                return new CommandResult(MESSAGE_EMPTY_ITEM_LIST);
            }
            model.addOrder(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }

        if (itemNameQuantityPairInput == null) {
            // Command has just been initiated
            return new CommandResult(MESSAGE_USAGE_2);
        }

        String itemName = itemNameQuantityPairInput.getKey();
        Quantity quantity = itemNameQuantityPairInput.getValue();

        if (quantity.value <= 0) {
            return new CommandResult(String.format(MESSAGE_INVALID_QUANTITY, quantity));
        }

        try {
            InventoryItem inventoryItem = model.getItem(itemName);
            toAdd.addToItemList(inventoryItem, quantity);
            return new CommandResult(String.format(MESSAGE_ADDED_ITEM, quantity + " * " + inventoryItem.getItemName()));
        } catch (ItemNotFoundException e) {
            return new CommandResult(String.format(MESSAGE_INVALID_ITEM, itemName));
        }

    }

    /**
     * Stages an item and quantity pair to be verified on intermediate command execution.
     * @param itemName The name of the item to be added to the order
     * @param quantity The quantity of the item to be added to the order
     */
    public void stageForValidation(String itemName, Quantity quantity) {
        this.itemNameQuantityPairInput = new Pair<>(itemName, quantity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && toAdd.equals(((AddOrderCommand) other).toAdd));
    }

}
