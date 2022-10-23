package seedu.address.logic.commands.client;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_PHONE;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientPhone;
import seedu.address.ui.Ui;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-e";

    public static final String MESSAGE_SUCCESS = "Edited Client: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a project in the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CLIENT_EMAIL + "CLIENT EMAIL "
            + PREFIX_CLIENT_PHONE + "PHONE "
            + PREFIX_CLIENT_ID + "CLIENT ID "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "John "
            + PREFIX_CLIENT_EMAIL + "john@gmail.com "
            + PREFIX_CLIENT_PHONE + "12345678 "
            + PREFIX_CLIENT_ID + "1 ";

    public static final String MESSAGE_INVALID_CLIENT = "Client does not exist.";

    private final Name newName;
    private final ClientEmail newEmail;
    private final ClientPhone newPhone;
    private final ClientId clientId;

    /**
     * Creates an EditClientCommand to edit the specified {@code Client}
     */
    public EditClientCommand(ClientId clientId, Name newName, ClientEmail newEmail, ClientPhone newPhone) {
        this.clientId = clientId;
        this.newName = newName;
        this.newEmail = newEmail;
        this.newPhone = newPhone;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        ui.showClients();
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        Client toEditClient = model.getClientById(clientId.getIdInt());

        if (newName != null) {
            toEditClient.setName(newName);
        }

        if (newEmail != null) {
            toEditClient.setEmail(newEmail);
        }

        if (newPhone != null) {
            toEditClient.setPhone(newPhone);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toEditClient));
    }
}
