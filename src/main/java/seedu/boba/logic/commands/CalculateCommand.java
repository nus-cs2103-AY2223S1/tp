package seedu.boba.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.boba.commons.core.Messages;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.CalculationParser;
import seedu.boba.model.BobaBotModel;


/**
 * A build-in calculator for bobaBot for cashiers to do basic arithmetic
 * calculation including +, -, /, -, with precedence enabled
 * e.g. calc 3*(1+4)/2
 */
public class CalculateCommand extends Command {

    /**
     * Command word for calculator.
     */
    public static final String COMMAND_WORD = "calc";

    /**
     * Message for calculator.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n"
            + "Calculate an arithmetic expression \n"
            + "Example: " + COMMAND_WORD + " 3*(1+2.4)";
    public static final String FEEDBACK_HEADER = "Calculator:\n";
    /**
     * Logger to record all the calculations performed
     * for future checking of bugs
     */
    private static Logger logger = Logger.getLogger("Calculation_History");

    private final String expression;

    /**
     * Constructor for calculator.
     * @param exp The arithmetic expression to be computed
     */
    public CalculateCommand(String exp) {
        this.expression = exp;
    }
    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) throws CommandException {

        requireNonNull(bobaBotModel);
        String calcResult;
        try {
            calcResult =
                    CalculationParser.parseCalculation(this.expression);
        } catch (NumberFormatException | EmptyStackException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ARITHMETIC_EXPRESSION);
        } catch (IllegalArgumentException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ARITHMETIC_EXPRESSION);
        }
        String feedback = this.expression + " = " + calcResult;
        logger.log(Level.INFO, feedback);
        return new CommandResult(FEEDBACK_HEADER + feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalculateCommand // instanceof handles nulls
                && expression.equals(((CalculateCommand) other).expression)); // state check
    }
}
