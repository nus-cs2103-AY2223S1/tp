package seedu.condonery.logic.commands.client;

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
 * Selects a client to view its details.
 */
public class SelectClientCommand extends Command {

    public static final String COMMAND_WORD = "select -c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects a client.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Client %1$s is selected and its details are currently shown below";

    private final Index targetIndex;

    /**
     * Creates a SelectCommand to select the specified {@code Client}
     */
    public SelectClientCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredClientList(client -> client.equals(clientToSelect));

        Set<Property> interestedProperties = clientToSelect.getInterestedProperties();
        model.updateFilteredPropertyList(property -> interestedProperties.contains(property));

        return new CommandResult(String.format(MESSAGE_SUCCESS, clientToSelect.getName().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectClientCommand // instanceof handles nulls
                && targetIndex.equals(((SelectClientCommand) other).targetIndex));
    }
}
