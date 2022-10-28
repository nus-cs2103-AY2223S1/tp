package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.utils.ParseClientInterestedProperties;

/**
 * Adds a property to Condonery.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_WORD = "add -c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to Condonery. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alice Tan "
            + PREFIX_ADDRESS + "Cantonment Rd, #1G, S085301 "
            + PREFIX_TAG + "Condo";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in Condonery";
    public static final String MESSAGE_IMAGE_UPLOAD = "Opened Upload Image window";

    private final Client toAdd;
    private final boolean hasImage;

    private final ArrayList<String> missingProperties = new ArrayList<>();
    private final ArrayList<String> duplicateProperties = new ArrayList<>();

    /**
     * Creates an AddCommand to add the specified {@code Property}
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
        hasImage = false;
    }

    /**
     * Creates an AddCommand to add the specified {@code Client}, with a boolean to indicate if image is uploaded.
     */
    public AddClientCommand(Client client, boolean hasImage) {
        requireNonNull(client);
        toAdd = client;
        this.hasImage = hasImage;
    }

    /**
     * Gets an updated sucess message based on the presence of missing properties or duplicate properties.
     */
    private String getUpdatedSuccessMessage(ArrayList<String> missingProperties,
                ArrayList<String> duplicateProperties) {
        String newSuccessMessage = MESSAGE_SUCCESS + ". ";

        if (missingProperties.isEmpty() && duplicateProperties.isEmpty()) {
            newSuccessMessage = newSuccessMessage + " No rejected property names.";
        } else {
            if (!missingProperties.isEmpty()) {
                newSuccessMessage = newSuccessMessage + "Missing properties: " + missingProperties
                        .stream()
                        .collect(Collectors.joining(" "))
                        + ". ";
            }
            if (!duplicateProperties.isEmpty()) {
                newSuccessMessage = newSuccessMessage + "Duplicate properties: " + duplicateProperties
                        .stream()
                        .collect(Collectors.joining(" "))
                        + ". ";
            }
        }
        return newSuccessMessage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }


        ParseClientInterestedProperties parser = new ParseClientInterestedProperties(
            toAdd, model);

        Client newClientToAdd = parser.getNewClient();
        newClientToAdd.setImageDirectoryPath(model.getUserPrefs().getUserImageDirectoryPath());

        String newMessageSuccess = getUpdatedSuccessMessage(parser.getMissingProperties(),
            parser.getDuplicateProperties());

        model.addClient(newClientToAdd);

        if (this.hasImage) {
            return new CommandResult(
                String.format(newMessageSuccess, toAdd),
                false,
                false,
                "client-" + newClientToAdd.getCamelCaseName()
            );
        }
        return new CommandResult(String.format(newMessageSuccess, newClientToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && toAdd.equals(((AddClientCommand) other).toAdd));
    }
}
