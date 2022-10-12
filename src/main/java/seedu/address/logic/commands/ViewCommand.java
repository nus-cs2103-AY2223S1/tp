package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTRIES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.Month;

/**
 * View income/expenditure entries to the application.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Listed all %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View income/expenditure entries to PennyWise. "
            + "Parameters: "
            + PREFIX_TYPE + "TYPE "
            + "[" + PREFIX_MONTH + "MONTH]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "e "
            + PREFIX_MONTH + "2022-03";

    private static final String ENTRY_EXPENDITURE = "expenditures";
    private static final String ENTRY_INCOME = "income";

    private final EntryType entryType;
    private final Month month;

    /**
     * Creates a ViewCommand to view the specified {@code entryType}.
     */
    public ViewCommand(EntryType entryType) {
        requireNonNull(entryType);
        this.entryType = entryType;
        this.month = null;
    }

    /**
     * Creates a ViewCommand to view the specified {@code entryType} at the specified {@code month}.
     */
    public ViewCommand(EntryType entryType, Month month) {
        requireNonNull(entryType);
        requireNonNull(month);
        this.entryType = entryType;
        this.month = month;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
            return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_EXPENDITURE));
        case INCOME:
            model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
            return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_INCOME));
        default:
            //should never reach here
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ViewCommand)) {
            return false;
        }
        ViewCommand otherViewCommand = (ViewCommand) other;
        if (this.month == null) {
            return this.entryType.equals(otherViewCommand.entryType);
        }
        return this.entryType.equals(otherViewCommand.entryType) && this.month.equals(otherViewCommand.month);
    }
}
