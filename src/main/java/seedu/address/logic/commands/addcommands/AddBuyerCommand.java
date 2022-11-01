package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;

/**
 * Adds a buyer to the address book.
 * If this buyer comes with multiple orders (where one order can have a pet as well), these orders and pets
 * will be also added to their respective unique lists.
 */
public class AddBuyerCommand extends AddPersonCommand {

    public static final String COMMAND_WORD = "add-b";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a buyer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_LOCATION + "LOCATION "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_LOCATION + "USA "
            + PREFIX_ORDER + AddOrderCommand.COMMAND_WORD + " (...Order1 fields)"
            + PREFIX_ORDER + AddOrderCommand.COMMAND_WORD + " (...Order2 fields)";

    public static final String MESSAGE_SUCCESS = "New buyer added: %1$s";
    public static final String MESSAGE_DUPLICATE_BUYER = "This buyer already exists in the buyer list";

    private final Buyer toAdd;
    private final List<Order> orders = new ArrayList<>();

    /**
     * Creates an AddBuyerCommand to add the specified {@code Buyer}.
     */
    public AddBuyerCommand(Buyer buyer, List<Order> orders) {
        requireNonNull(buyer);
        toAdd = buyer;
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBuyer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BUYER);
        }

        List<Order> orders = this.orders;
        int numOrdersAdded = orders.size();

        for (Order order : orders) {
            if (model.hasOrder(order)) {
                throw new CommandException(AddOrderCommand.MESSAGE_DUPLICATE_ORDER);
            }
        }

        for (Order order : orders) {
            model.addOrder(order);
            order.setBuyer(toAdd);
        }

        toAdd.addOrders(orders.stream().map(Order::getId).collect(Collectors.toList()));
        model.addBuyer(toAdd);

        return new CommandResult("\n" //TODO To keep a single MESSAGE_SUCCESS
                + numOrdersAdded + (numOrdersAdded == 1 ? " order" : " orders") + " added\n"
                + String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBuyerCommand // instanceof handles nulls
                && toAdd.equals(((AddBuyerCommand) other).toAdd));
    }
}
