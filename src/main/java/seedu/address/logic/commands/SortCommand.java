package seedu.address.logic.commands;

import static java.util.Objects.compare;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTION_SPECIFIED_CLIENT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.client.NameEqualsKeywordPredicate;

import java.util.List;

/**
 * Finds and lists all clients in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts specified client's transaction by either "
            + "latest transaction or oldest transaction.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "[latest]/[oldest].\n"
            + "Example: " + COMMAND_WORD + " 1 " + "latest";


    public static final String MESSAGE_SUCCESS = "Sorted by %1$s transactions for index %2$s.";

    private final boolean isLatest;

    private final Index index;

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
                false, false, false, true);
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

        // state check
        SortCommand e = (SortCommand) other;
        return isLatest == e.isLatest;
    }

}
