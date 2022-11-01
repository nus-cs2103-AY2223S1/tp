package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NON_EXISTING_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;

/**
 * Adds a client to MyInsuRec.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_WORD = "addClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to MyInsuRec. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY]"
            + "[" + PREFIX_PRODUCT + "PRODUCT(S)]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_BIRTHDAY + "20121989 "
            + PREFIX_PRODUCT + "Product1";


    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in MyInsuRec";


    private final Client toAdd;

    /**
     * Creates an AddClientCommand to add the specified {@code Client}
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!toAdd.isBirthdayValid()) {
            throw new CommandException(Birthday.MESSAGE_DATE_CONSTRAINTS);
        }

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        if (toAdd.getProducts()
                .stream()
                .map(model::hasProduct)
                .anyMatch(entry -> !entry)) {
            throw new CommandException(MESSAGE_NON_EXISTING_PRODUCT);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandSpecific.CLIENT);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && toAdd.equals(((AddClientCommand) other).toAdd));
    }
}
