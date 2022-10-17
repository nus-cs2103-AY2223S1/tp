package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_PHONE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_PROJECT_ID;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.ui.Ui;

/**
 * Adds a client to the address book.
 */
public class AddClientCommand extends ClientCommand {

    public static final String COMMAND_FLAG = "-a";

    public static final String MESSAGE_ADD_CLIENT_USAGE = COMMAND_WORD + ": Adds a client to the address book. "
            + "Parameters: "
            + PREFIX_CLIENT_NAME + "NAME "
            + PREFIX_CLIENT_PHONE + "PHONE "
            + PREFIX_CLIENT_EMAIL + "EMAIL "
            + PREFIX_PROJECT_ID + "PROJECT ID: "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_CLIENT_NAME + "John Doe "
            + PREFIX_CLIENT_PHONE + "98765432 "
            + PREFIX_CLIENT_EMAIL + "johnd@example.com "
            + PREFIX_PROJECT_ID + "1";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book";
    private static final String MESSAGE_EXISTING_CLIENT = "This project already has a client";

    private final Name toAddClientName;
    private final ClientPhone toAddClientPhone;
    private final ClientEmail toAddClientEmail;
    private final ProjectId toModifyProjectId;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddClientCommand(Name clientName, ClientPhone clientPhone, ClientEmail clientEmail,
                             ProjectId projectId) {
        requireNonNull(clientName);
        requireNonNull(clientPhone);
        requireNonNull(clientEmail);
        requireNonNull(projectId);

        toAddClientName = clientName;
        toAddClientPhone = clientPhone;
        toAddClientEmail = clientEmail;
        toModifyProjectId = projectId;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);

        Project toModifyProject = model.getProjectById(toModifyProjectId.getIdInt());
        ClientId toAddClientId = new ClientId(model.generateClientId());

        if (model.hasClientId(toAddClientId.getIdInt())) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }
        if (!toModifyProject.getClient().isEmpty()) {
            throw new CommandException(MESSAGE_EXISTING_CLIENT);
        }

        Client toAddClient = new Client(
                toAddClientName, toAddClientPhone, toAddClientEmail, );
        toModifyProject.setClient(toAddClient);

        model.addClient(toAddClient);

        ui.showClients();
        model.updateFilteredClientList(Model.PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddClient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
        //        && toAddClientId.equals(((AddClientCommand) other).toAddClientId) TODO: review (ids are not used)
        );
    }}
