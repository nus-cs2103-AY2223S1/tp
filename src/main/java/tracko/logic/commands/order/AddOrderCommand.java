package tracko.logic.commands.order;

import static java.util.Objects.requireNonNull;

import javafx.util.Pair;
import tracko.logic.commands.CommandResult;
import tracko.logic.commands.MultiLevelCommand;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.CliSyntax;
import tracko.model.Model;
import tracko.model.order.ItemQuantityPair;
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


    public static final String MESSAGE_SUCCESS = "New order added:\n%1$s";

    private final Order toAdd;
    private Pair<String, Integer> lastItemQuantityPair;

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
        if (this.isCancelled) {
            return new CommandResult("Add Order Command Cancelled");
        }

        if (this.isAwaitingInput() && lastItemQuantityPair != null) {
            return new CommandResult(String.format(MESSAGE_ADDED_ITEM, lastItemQuantityPair));
        } else if (this.isAwaitingInput()) {
            return new CommandResult(MESSAGE_USAGE_2);
        }

        requireNonNull(model);
        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Adds an item and quantity pair to the item list.
     * @param itemQuantityPair The item and quantity pair
     */
    public void addToItemList(ItemQuantityPair itemQuantityPair) {
        this.toAdd.addToItemList(itemQuantityPair);
        this.lastItemQuantityPair = itemQuantityPair;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && toAdd.equals(((AddOrderCommand) other).toAdd));
    }

}
