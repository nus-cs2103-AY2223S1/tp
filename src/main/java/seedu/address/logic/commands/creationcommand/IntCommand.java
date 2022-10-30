package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class IntCommand extends PureCommand {

    public static final String COMMAND_WORD = "int";
    private static final String INVALID_INPUT = "The input is not an integer";

    private Integer num;

    public IntCommand(Integer num) {
        this.num = num;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format("created %d", num), false, false, num);
    }

    public static Parser<IntCommand> parser() {
        return new Parser<IntCommand>() {
            @Override
            public IntCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                if (userInput.length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                Integer num;
                try {
                    num = Integer.parseInt(userInput);
                } catch (NumberFormatException e) {
                    throw new ParseException(INVALID_INPUT);
                }
                return new IntCommand(num);
            }

        };
    }
}
