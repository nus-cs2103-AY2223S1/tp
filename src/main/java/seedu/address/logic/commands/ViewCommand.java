package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ENTRIES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.EntryType;

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
            + PREFIX_MONTH + "09-2022 ";
    public static final String MESSAGE_INVALID_ENTRY_TYPE = "Entry type is invalid.";

    private static final String ENTRY_EXPENDITURE = "expenditures";
    private static final String ENTRY_INCOME = "income";

    private final EntryType entryType;

    /**
     * Creates a ViewCommand to view the specified {@code entryType}
     */
    public ViewCommand(EntryType entryType) {
        requireNonNull(entryType);
        this.entryType = entryType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            System.out.println("[ViewCommand] Show all expenditure");
            model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
            return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_EXPENDITURE));
        case INCOME:
            System.out.println("[ViewCommand] Show all income");
            model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
            return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_INCOME));
        default:
            //should never reach here
            break;
        }

        throw new CommandException(MESSAGE_INVALID_ENTRY_TYPE);
    }
}
