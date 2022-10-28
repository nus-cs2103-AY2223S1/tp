package seedu.address.logic.commands.logicalcommand;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class CmpCommand extends Command {

    public static final String COMMAND_WORD = "cmp";
    private static final String INVALID_INPUT = "The unknown operator";
    private static final String USE_MESSAGE = "op [< <= > >= == !=] Object";
    private static final String RUNTIME_ERR = "Unable to convert to number";
    private static final String DIV0 = "Cannot divide by zero";
    private static final String MISSINGINPUT = "Missing input of type float";

    private Object num = null;
    private Function<Object, Boolean> func;

    public CmpCommand(String op, String opNum) throws ParseException {
        switch (op) {
            case "==":
                func = x -> opNum.equals(x.toString());
                break;
            case "<":
                func = x -> opNum.compareTo(x.toString()) < 0;
                break;
            case ">":
                func = x -> opNum.compareTo(x.toString()) > 0;
                break;
            case "!=":
                func = x -> !opNum.equals(x.toString());
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

    public static Parser<CmpCommand> parser() {
        return new Parser<CmpCommand>() {
            @Override
            public CmpCommand parse(String userInput) throws ParseException {
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
                String val = res.group(2);
                System.out.printf("%s: %f", op, val);
                return new CmpCommand(op, val);
            }

        };
    }

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null) {
            throw new CommandException(RUNTIME_ERR);
        }
        if (!(additionalData instanceof Float)) {
            try {
                num = Float.parseFloat(additionalData.toString());
            } catch (NumberFormatException e) {
                throw new CommandException(RUNTIME_ERR);
            }
            return;
        }

        num = (Float) additionalData;
    }
}
