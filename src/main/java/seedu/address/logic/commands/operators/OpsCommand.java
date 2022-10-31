package seedu.address.logic.commands.operators;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Command to do arithmatic operations
 */
public class OpsCommand extends Command {

    public static final String COMMAND_WORD = "ops";
    private static final String INVALID_INPUT = "The unknown operator";
    private static final String USE_MESSAGE = "op [+-*/] number";
    private static final String RUNTIME_ERR = "Unable to convert to number";
    private static final String DIV0 = "Cannot divide by zero";
    private static final String MISSINGINPUT = "Missing input of type float";

    private Float num = null;
    private Function<Float, Float> func;

    /**
     * Constructor for operation command
     */
    public OpsCommand(String op, Float opNum) throws ParseException {
        switch (op) {
        case "/":
            if (opNum == 0) {
                throw new ParseException(DIV0);
            }
            func = x -> x / opNum;
            break;
        case "*":
            func = x -> x * opNum;
            break;
        case "+":
            func = x -> x + opNum;
            break;
        case "-":
            func = x -> x - opNum;
            break;
        default:
            assert false;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (num == null) {
            throw new CommandException(MISSINGINPUT);
        }
        return new CommandResult(String.format("result: %f", func.apply(num)), false, false, func.apply(num));
    }

    /**
     * Parser to parse user input operations command
     *
     * @return
     */
    public static Parser<OpsCommand> parser() {
        return new Parser<OpsCommand>() {
            @Override
            public OpsCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                System.out.println(userInput);
                if (userInput.length() == 0) {
                    throw new ParseException(INVALID_INPUT + "\n" + USE_MESSAGE);
                }
                Matcher res = Pattern.compile("([\\/+\\-*])\\s*([\\-+]?[0-9]+[.]?[0-9]*)\\s*").matcher(userInput);
                if (!res.matches()) {
                    throw new ParseException(INVALID_INPUT + "\n" + USE_MESSAGE);
                }
                String op = res.group(1);
                Float val = Float.parseFloat(res.group(2));
                System.out.printf("%s: %f", op, val);
                return new OpsCommand(op, val);
            }

        };
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null) {
            throw new CommandException(RUNTIME_ERR);
        }
        if (!(additionalData instanceof Float)) {
            try {
                num = Float.parseFloat(additionalData.toString());
            } catch (NumberFormatException e) {
                throw new CommandException(RUNTIME_ERR);
            }
            return this;
        }

        num = (Float) additionalData;
        return this;
    }
}
