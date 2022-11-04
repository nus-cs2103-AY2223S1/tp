package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_IMAGE_UPLOAD;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to Condonery. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PROPERTY_TYPE + "PROPERTY_TYPE "
            + "[" + PREFIX_IMAGE_UPLOAD + "] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "PINNACLE@DUXTON "
            + PREFIX_ADDRESS + "Cantonment Rd, #1G, S085301 "
            + PREFIX_PRICE + "1000000 "
            + PREFIX_PROPERTY_TYPE + "HDB "
            + PREFIX_PROPERTY_STATUS + "SOLD "
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

    /**
     * Gets an updated sucess message based on the presence of missing clients or duplicate clients.
     */
    private String getUpdatedSuccessMessage(ArrayList<String> missingClients, ArrayList<String> duplicateClients) {
        String newSuccessMessage = MESSAGE_SUCCESS + ". ";

        if (missingClients.isEmpty() && duplicateClients.isEmpty()) {
            newSuccessMessage = newSuccessMessage + "No rejected client names.";
        } else {
            if (!missingClients.isEmpty()) {
                newSuccessMessage = newSuccessMessage + "Missing clients: " + missingClients
                        .stream()
                        .collect(Collectors.joining(" "))
                        + ". ";
            }
            if (!duplicateClients.isEmpty()) {
                newSuccessMessage = newSuccessMessage + "Duplicate clients: " + duplicateClients
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

        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }
        toAdd.setImageDirectoryPath(model.getUserPrefs().getUserImageDirectoryPath());

        ParsePropertyInterestedClients parser = new ParsePropertyInterestedClients(
                toAdd, model);

        Property newPropertyToAdd = parser.getNewProperty();

        String newMessageSuccess = getUpdatedSuccessMessage(parser.getMissingClients(), parser.getDuplicateClients());

        model.addProperty(newPropertyToAdd);
        if (this.hasImage) {
            return new CommandResult(
                    String.format(newMessageSuccess, newPropertyToAdd),
                    false,
                    false,
                    "property-" + newPropertyToAdd.getCamelCaseName());
        }
        return new CommandResult(String.format(newMessageSuccess, newPropertyToAdd));
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
