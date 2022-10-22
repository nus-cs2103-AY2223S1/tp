package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.Property;

/**
 * Adds a property to Condonery.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "add -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to Condonery. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "PINNACLE@DUXTON "
            + PREFIX_ADDRESS + "Cantonment Rd, #1G, S085301 "
            + PREFIX_TAG + "High-End "
            + PREFIX_TAG + "Available";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in Condonery";
    public static final String MESSAGE_IMAGE_UPLOAD = "Opened Upload Image window";

    private final Property toAdd;
    private final boolean hasImage;

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

        model.addProperty(toAdd);
        if (this.hasImage) {
            return new CommandResult(
                String.format(MESSAGE_SUCCESS, toAdd),
                false,
                false,
                "property-" + toAdd.getCamelCaseName()
            );
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyCommand // instanceof handles nulls
                && toAdd.equals(((AddPropertyCommand) other).toAdd));
    }
}
