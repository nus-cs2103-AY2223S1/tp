package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.List;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.Property;

/**
 * Deletes a property from Condonery.
 */
public class DeletePropertyCommand extends Command {

    public static final String COMMAND_WORD = "delete -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a property from Condonery.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROPERTY_SUCCESS = "Property successfully deleted: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteCommand to delete the specified {@code Property}
     */
    public DeletePropertyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Property> lastShownList = model.getFilteredPropertyList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToDelete = lastShownList.get(targetIndex.getZeroBased());
        File image = new File(propertyToDelete.getImagePath().toString());
        image.delete();
        model.deleteProperty(propertyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROPERTY_SUCCESS, propertyToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePropertyCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePropertyCommand) other).targetIndex)); // state check
    }
}
