package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;

/**
 * Adds an income/expenditure entry to the application.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a income/expenditure entry to PennyWise. "
        + "Parameters: "
        + PREFIX_TYPE + "TYPE "
        + PREFIX_DESCRIPTION + "DESCRIPTION "
        + PREFIX_AMOUNT + "AMOUNT "
        + PREFIX_DATE + "DATE "
        + PREFIX_TAG + "TAG \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TYPE + "e "
        + PREFIX_DESCRIPTION + "Lunch "
        + PREFIX_AMOUNT + "7.20 "
        + PREFIX_DATE + "4-10-2022 "
        + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the penny wise application";

    final Entry toAdd;
    final EntryType entryType;

    /**
     * Creates an AddEntryCommand to add the specified {@code Expenditure}
     */
    public AddCommand(Entry entry, EntryType entryType) {
        requireNonNull(entry);
        toAdd = entry;
        this.entryType = entryType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExpenditure(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            model.addExpenditure(toAdd);
            break;
        case INCOME:
            model.addIncome(toAdd);
            break;
        default:
            // should never reach here
            return null;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
