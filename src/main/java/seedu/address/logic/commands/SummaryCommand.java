package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SummaryCommandParser;
import seedu.address.model.Model;
import seedu.address.model.entry.Date;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a summary of financials."
            + "Parameters: "
            + "[" + PREFIX_DATE + "DATE] .... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "10-2022";

    public static final String MESSAGE_SUCCESS = "Financials Summarized";

    final Date date;

    public SummaryCommand(Date date) {
        this.date = date;
    }

    public SummaryCommand() {
        this.date = null;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
