package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import paymelah.model.Model;
import paymelah.model.person.Person;

/**
 * Displays the total amount of money the user is owed from the filtered person list to the user.
 */
public class StatementCommand extends Command {

    public static final String COMMAND_WORD = "statement";

    public static final String MESSAGE_SUCCESS = "You are owed $%1$s in total.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        BigDecimal totalSum = new BigDecimal(0).setScale(2);
        for (Person person : model.getFilteredPersonList()) {
            totalSum = totalSum.add(person.getDebts().getTotalDebt());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, totalSum));
    }
}
