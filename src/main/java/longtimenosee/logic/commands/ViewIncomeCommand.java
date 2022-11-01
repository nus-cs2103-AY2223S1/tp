package longtimenosee.logic.commands;

import java.time.LocalDate;

import longtimenosee.model.Model;
import longtimenosee.model.policy.FinancialAdvisorIncome;

/**
 * Allows user to view projected 3 year income upon command.
 */
public class ViewIncomeCommand extends Command {

    public static final String COMMAND_WORD = "viewIncome";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <year>"
            + ": chooses the year for viewing income"
            + "\nNote: Year should in format YYYY e.g. 2000";
    private final int targetYear;

    public ViewIncomeCommand(int targetYear) {
        this.targetYear = targetYear;
    }

    @Override
    public CommandResult execute(Model model) {
        LocalDate date = LocalDate.of(targetYear, 1, 1);
        FinancialAdvisorIncome income = model.getIncome();
        income.threeYearIncome(date, model);
        float result = income.calculateIncome(date, model);
        String message = "Income for year " + targetYear + " is " + result;
        return new CommandResult(message, false, false, false,
                false, false, true);
    }
}

