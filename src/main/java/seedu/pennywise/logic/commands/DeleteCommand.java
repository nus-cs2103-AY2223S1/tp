package seedu.pennywise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.List;

import seedu.pennywise.commons.core.Messages;
import seedu.pennywise.commons.core.index.Index;
import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;

/**
 * Deletes an entry identified using it's displayed index from the PennyWise application.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the entry identified by the index number used in the displayed entry list.\n"
        + "Parameters: INDEX (must be a positive integer) " + PREFIX_TYPE + "TYPE\n"
        + "Example: " + COMMAND_WORD + " 1 t/e";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Deleted Entry: %1$s";

    final EntryType entryType;
    private final Index targetIndex;

    /**
     * Creates a DeleteCommand with the {@code targetIndex} and {@code entryType}
     */
    public DeleteCommand(Index targetIndex, EntryType entryType) {
        this.targetIndex = targetIndex;
        this.entryType = entryType;
    }

    private Entry getEntryToDelete(Index targetIndex, List<Entry> lastShownList)
            throws CommandException {
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }
        return lastShownList.get(targetIndex.getZeroBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList;
        Entry entryToDelete;
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            lastShownList = model.getFilteredExpenditureList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
            }

            entryToDelete = getEntryToDelete(targetIndex, lastShownList);
            model.deleteExpenditure(entryToDelete);
            break;
        case INCOME:
            lastShownList = model.getFilteredIncomeList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
            }

            entryToDelete = getEntryToDelete(targetIndex, lastShownList);
            model.deleteIncome(entryToDelete);
            break;
        default:
            // should never reach here
            return null;
        }
        return new CommandResult(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete),
                false,
                false,
                GraphConfiguration.UPDATE_CURR_GRAPH_CONFIG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
