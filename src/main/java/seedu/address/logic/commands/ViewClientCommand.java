package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Views a client.
 */
public class ViewClientCommand extends Command {
    public static final String COMMAND_WORD = "viewClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the client identified by the index number used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " i/1";

    public static final String MESSAGE_SUCCESS = "Viewed client: %1$s";

    private final Index targetIndex;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public ViewClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_SUCCESS, clientToView), CommandSpecific.DETAILED_CLIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClientCommand // instanceof handles nulls
                && targetIndex.equals(((ViewClientCommand) other).targetIndex)); // state check
    }
}
