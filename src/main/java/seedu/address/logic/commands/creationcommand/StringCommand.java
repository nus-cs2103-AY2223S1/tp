package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class StringCommand extends PureCommand {

    public static final String COMMAND_WORD = "str";
    private static final String INVALID_INPUT = "The input cannot be empty";

    private String val;

    public StringCommand(String val) {
        this.val = val;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format("created %s", val), false, false, val);
    }

    public static Parser<StringCommand> parser() {
        return new Parser<StringCommand>() {
            @Override
            public StringCommand parse(String userInput) throws ParseException {
                String val = userInput.trim();
                if (val.length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                val = userInput;
                return new StringCommand(val);
            }

        };
    }
}
