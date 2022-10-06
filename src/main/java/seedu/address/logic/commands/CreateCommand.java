package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.poc.Poc;

/**
 * Changes the remark of an existing person in the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";


    //Update here
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_COY + "[Create]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COY + "Likes to swim.";

    //Update here
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;

    private final Poc POC;

    /**
     * @param index of the company in the company list to add the POC to
     * @param POC to be added
     */

    public CreateCommand(Index index, Poc POC) {
        this.index = index;
        this.POC = POC;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateCommand)) {
            return false;
        }

        // state check
        CreateCommand e = (CreateCommand) other;
        return index.equals(e.index)
                && create.equals(e.create);
        return false;
    }
}
