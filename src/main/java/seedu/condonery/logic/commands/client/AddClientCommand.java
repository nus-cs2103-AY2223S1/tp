package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.property.Property;

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
    private final Set<Property> interestedProperties = new HashSet<>();

    private final ArrayList<String> missingProperties = new ArrayList<>();
    private final ArrayList<String> duplicateProperties = new ArrayList<>();

    /**
     * Constructor for AddClientCommand to add the specified {@code Client}
     * @param client
     */
    public AddClientCommand(Client client) {
        requireNonNull(client);
        toAdd = client;
        hasImage = false;

    }
    /**
     * Creates an AddCommand to add the specified {@code Property}
     */
    public AddClientCommand(Client client, Set<Property> interestedProperties) {
        requireNonNull(client);
        toAdd = client;
        hasImage = false;
        this.interestedProperties.addAll(interestedProperties);
    }

    /**
     * Creates an AddCommand to add the specified {@code Client}, with a boolean to indicate if image is uploaded.
     */
    public AddClientCommand(Client client, Set<Property> interestedProperties, boolean hasImage) {
        requireNonNull(client);
        toAdd = client;
        this.hasImage = hasImage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Property property;

        if (model.hasClient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        for (Property interestedProperty : interestedProperties) {
            String interestedPropertyName = interestedProperty.getName().toString();
            if (!model.hasPropertyName(interestedPropertyName)) {
                throw new CommandException("Could not find any property matching substring "
                    + interestedPropertyName
                    + ". You might want to refine your search.");
            }
            if (!model.hasUniquePropertyName(interestedPropertyName)) {
                if ((property = model.getPropertyByExactName(interestedPropertyName)) == null) {
                    throw new CommandException("More than 1 property matches substring "
                        + interestedPropertyName
                        + ". You might want to make your search more specific, or use the exact name of the property");
                }
            } else {
                property = model.getUniquePropertyByName(interestedPropertyName);
            }
            property.getInterestedClients().add(toAdd);
        }

        toAdd.setImageDirectoryPath(model.getUserPrefs().getUserImageDirectoryPath());

        model.addClient(toAdd);

        if (this.hasImage) {
            return new CommandResult(
                String.format(MESSAGE_SUCCESS, toAdd),
                false,
                false,
                "client-" + toAdd.getCamelCaseName()
            );
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && toAdd.equals(((AddClientCommand) other).toAdd));
    }
}
