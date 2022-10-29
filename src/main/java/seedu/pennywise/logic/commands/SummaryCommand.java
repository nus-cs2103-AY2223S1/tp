package seedu.pennywise.logic.commands;

import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_MONTH;

import java.util.List;
import java.util.function.Predicate;

import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryInYearMonthPredicate;
import seedu.pennywise.model.entry.GraphType;


/**
 * Summarizes the financials of the user.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a summary of financials."
            + "Parameters: "
            + "[" + PREFIX_MONTH + "MONTH] .... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MONTH + "2022-10";

    public static final String MESSAGE_SUCCESS = "Total Expenditure: $%.2f\n"
            + "Total Income: $%.2f\n"
            + "Total Balance: $%.2f";

    private final Predicate<Entry> predicate;

    public SummaryCommand(EntryInYearMonthPredicate predicate) {
        this.predicate = predicate;
    }

    public SummaryCommand() {
        this.predicate = null;
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
        GraphConfiguration graphConfiguration = null;
        if (this.predicate != null) {
            model.updateFilteredEntryList(this.predicate);
            graphConfiguration = new GraphConfiguration(
                    null,
                    new GraphType(GraphType.GRAPH_TYPE_MONTH),
                    true);
        } else {
            graphConfiguration = new GraphConfiguration(null, null, false);
        }
        assert graphConfiguration != null;
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
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, totalExpenditure, totalIncome, totalBalance),
                false,
                false,
                graphConfiguration);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SummaryCommand
                && (this.predicate == null && ((SummaryCommand) other).predicate == null)// instanceof handles nulls
                || this.predicate.equals(((SummaryCommand) other).predicate)); // state check
    }
}
