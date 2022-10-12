package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_INCOME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_RISK_APPETITE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.client.Client;


/**
 * Adds a client to the address book.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_WORD = "client"; //TODO: This will eventually replace add

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Client to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG] (multiple)... "
            + PREFIX_BIRTHDAY + "BIRTHDAY "
            + PREFIX_INCOME + "INCOME "
            + PREFIX_RISK_APPETITE + "RA.. \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney"
            + PREFIX_BIRTHDAY + "2021-03-03 "
            + PREFIX_INCOME + "21.0 "
            + PREFIX_RISK_APPETITE + "m ";


    public static final String MESSAGE_SUCCESS = "New Client added: %1$s";

    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book";
    private final Client toAdd;

    /**
     * Main constructor for AddClient
     * @param client
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && toAdd.equals(((AddClientCommand) other).toAdd));
    }
}
