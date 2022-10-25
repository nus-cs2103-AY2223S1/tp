package longtimenosee.logic.commands;

import java.time.LocalDate;

import longtimenosee.model.Model;
import longtimenosee.model.policy.FinancialAdvisorIncome;

/**
 * Allows user to view projected 3 year income upon command.
 */
public class ViewIncomeCommand extends Command {

    public static final String COMMAND_WORD = "viewIncome"; //should I change to refresh?

    public static final String MESSAGE_USAGE = COMMAND_WORD + "<YEAR>" + ": chooses the year for viewing income";
    private final int targetYear;

    public ViewIncomeCommand(int targetYear) {
        this.targetYear = targetYear;
    }

    @Override
    public CommandResult execute(Model model) {
        LocalDate date = LocalDate.of(targetYear, 1, 1);
        FinancialAdvisorIncome i = model.getIncome();
        i.threeYearIncome(date, model);
        float result = i.calculateIncome(date, model);
        String message = "Income for year " + targetYear + " is " + result;
        return new CommandResult(message, false, false, false,
                false, false, true);
    }
}

