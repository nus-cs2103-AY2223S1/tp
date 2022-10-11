package seedu.condonery.logic.commands.property;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.Property;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeletePropertyCommand extends Command {

    public static final String COMMAND_WORD = "delete -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a property from Condonery. "
            + "Parameters: "
            + "<INDEX>";

    public static final String MESSAGE_DELETE_PROPERTY_SUCCESS  = "Property successfully deleted: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteCommand to delete the specified {@code Property}
     */
    public DeletePropertyCommand(Index targetIndex) {
        requireNonNull(targetIndex);
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
        model.deleteProperty(propertyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROPERTY_SUCCESS, propertyToDelete));

    }
}
