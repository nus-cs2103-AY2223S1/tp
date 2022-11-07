package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_IMAGE_UPLOAD;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_INTERESTEDCLIENTS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.utils.ParsePropertyInterestedClients;

/**
 * Adds a property to Condonery.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "add -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to Condonery. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PROPERTY_TYPE + "PROPERTY_TYPE "
            + PREFIX_PRICE + "PRICE "
            + "[" + PREFIX_IMAGE_UPLOAD + "] "
            + "[" + PREFIX_PROPERTY_STATUS + "PROPERTY_STATUS] "
            + "[" + PREFIX_INTERESTEDCLIENTS + "INTERESTED_CLIENT]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "PINNACLE@DUXTON "
            + PREFIX_ADDRESS + "Cantonment Rd, #1G, S085301 "
            + PREFIX_PRICE + "1000000 "
            + PREFIX_PROPERTY_TYPE + "HDB "
            + PREFIX_PROPERTY_STATUS + "SOLD "
            + PREFIX_INTERESTEDCLIENTS + "Bobby "
            + PREFIX_TAG + "High-End ";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in Condonery";
    public static final String MESSAGE_IMAGE_UPLOAD = "Opened Upload Image window";

    private final Property toAdd;
    private final boolean hasImage;
    private final ArrayList<String> missingClients = new ArrayList<>();
    private final ArrayList<String> duplicateClients = new ArrayList<>();

    /**
     * Creates an AddCommand to add the specified {@code Property}
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        this.toAdd = property;
        this.hasImage = false;
    }

    /**
     * Creates an AddCommand to add the specified {@code Property}, with a boolean to indicate if image is uploaded.
     */
    public AddPropertyCommand(Property property, boolean hasImage) {
        requireNonNull(property);
        toAdd = property;
        this.hasImage = hasImage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        toAdd.setImageDirectoryPath(model.getUserPrefs().getUserImageDirectoryPath());

        ParsePropertyInterestedClients parser = new ParsePropertyInterestedClients(
                toAdd, model);

        // Throws CommandException if the user inputs clients that are missing
        List<String> missingClients = parser.getMissingClients();
        if (missingClients.size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Could not find the interested clients: ");
            missingClients.forEach(client -> builder.append(client + ", "));
            String result = builder.toString();
            throw new CommandException(result.substring(0, result.length() - 2));
        }

        // Throws CommandException if the user inputs clients that have multiple results
        List<String> duplicateClients = parser.getDuplicateClients();
        if (duplicateClients.size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("More than 1 client matches the search result for: ");
            duplicateClients.forEach(client -> builder.append(client + ", "));
            String result = builder.toString();
            String errorMessage = result.substring(0, result.length() - 2)
                + ". You might want to make your search more specific, or use the exact name of the client";
            throw new CommandException(errorMessage);
        }

        Property newPropertyToAdd = parser.getNewProperty();

        model.addProperty(newPropertyToAdd);
        if (this.hasImage) {
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS, newPropertyToAdd),
                    false,
                    false,
                    "property-" + newPropertyToAdd.getName().toString());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, newPropertyToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyCommand // instanceof handles nulls
                && toAdd.equals(((AddPropertyCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return toAdd.toString();
    }
}
