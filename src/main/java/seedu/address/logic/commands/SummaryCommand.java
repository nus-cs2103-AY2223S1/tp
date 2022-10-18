package seedu.address.logic.commands;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Entry;


/**
 * Summarizes the financials of the user
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a summary of financials."
            + "Parameters: "
            + "[" + PREFIX_DATE + "DATE] .... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "10-2022";

    public static final String MESSAGE_SUCCESS = "Financials Summarized \n"
            + "Total Expenditure: %.2f\n"
            + "Total Income: %.2f\n"
            + "Total Balance: %.2f";

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
        List<Entry> expenditureList = model.getFilteredExpenditureList();
        List<Entry> incomeList = model.getFilteredIncomeList();
        Double totalExpenditure = expenditureList
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().toString()))
                .sum();
        Double totalIncome = incomeList
                .stream()
                .mapToDouble(entry -> Double.parseDouble(entry.getAmount().toString()))
                .sum();
        Double totalBalance = totalIncome - totalExpenditure;
        return new CommandResult(String.format(MESSAGE_SUCCESS, totalExpenditure, totalIncome, totalBalance));
    }
}
