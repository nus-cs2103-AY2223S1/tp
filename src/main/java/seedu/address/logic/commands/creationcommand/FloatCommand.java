package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class FloatCommand extends PureCommand {
    public static final String COMMAND_WORD = "float";
    private static final String INVALID_INPUT = "The input is not an float";

    private Float num;

    public FloatCommand(Float num) {
        this.num = num;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format("created %f", num), false, false, num);
    }

    public static Parser<FloatCommand> parser() {
        return new Parser<FloatCommand>() {
            @Override
            public FloatCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                if (userInput.length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                Float num;
                try {
                    num = Float.parseFloat(userInput);
                } catch (NumberFormatException e) {
                    throw new ParseException(INVALID_INPUT);
                }
                return new FloatCommand(num);
            }

        };
    }
}
