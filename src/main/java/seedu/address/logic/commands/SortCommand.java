package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;


/**
 * Sorts a specified client in JeeqTracker by either latest or oldest transaction.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts specified client's transaction by either "
            + "latest transaction or oldest transaction.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "ORDER (must be either 'latest' or 'oldest').\n"
            + "Example: " + COMMAND_WORD + " 1 " + "latest";


    public static final String MESSAGE_SUCCESS = "Sorted by %1$s transactions for index %2$s.";

    private final boolean isLatest;

    private final Index index;

    /**
     * @param index of the client in the client list to sort.
     * @param isLatest checks if transactions sort by latest or oldest.
     */
    public SortCommand(Index index, boolean isLatest) {
        requireNonNull(index);
        this.isLatest = isLatest;
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToView = lastShownList.get(index.getZeroBased());
        String clientName = clientToView.getName().toString();

        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate(clientName);
        model.updateFilteredClientList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, isLatest ? "latest" : "oldest", index.getOneBased()),
                false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // same index
        if (!index.equals(((SortCommand) other).index)) {
            return false;
        }

        // state check
        SortCommand e = (SortCommand) other;
        return isLatest == e.isLatest;
    }

}
