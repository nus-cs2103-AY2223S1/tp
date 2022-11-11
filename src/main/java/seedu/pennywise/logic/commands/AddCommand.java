package seedu.pennywise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;

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
        + PREFIX_DATE + "04-10-2022 "
        + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the PennyWise application";

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

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            if (model.hasExpenditure(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
            }
            model.addExpenditure(toAdd);
            break;
        case INCOME:
            if (model.hasIncome(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
            }
            model.addIncome(toAdd);
            break;
        default:
            // should never reach here
            return null;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                false,
                false,
                GraphConfiguration.UPDATE_CURR_GRAPH_CONFIG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
