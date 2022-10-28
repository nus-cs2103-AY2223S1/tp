package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.property.Property;

/**
 * Selects a property to view its details.
 */
public class SelectPropertyCommand extends Command {

    public static final String COMMAND_WORD = "select -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects a property.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Property %1$s is selected and its details are currently shown below";

    private final Index targetIndex;

    /**
     * Creates a SelectCommand to select the specified {@code Property}
     */
    public SelectPropertyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Property> lastShownList = model.getFilteredPropertyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredPropertyList(property -> property.equals(propertyToSelect));

        Set<Client> interestedClients = propertyToSelect.getInterestedClients();
        model.updateFilteredClientList(client -> interestedClients.contains(client));

        return new CommandResult(String.format(MESSAGE_SUCCESS, propertyToSelect.getName().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectPropertyCommand // instanceof handles nulls
                && targetIndex.equals(((SelectPropertyCommand) other).targetIndex));
    }
}
