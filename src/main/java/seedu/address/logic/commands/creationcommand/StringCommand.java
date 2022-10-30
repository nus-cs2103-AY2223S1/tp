package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class StringCommand extends PureCommand {

    public static final String COMMAND_WORD = "str";
    private static final String INVALID_INPUT = "The input cannot be empty";

    private String val;
    private String next;

    public StringCommand(String val, String next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (next == null || next.equals("")) {
            return new CommandResult(String.format("created %s", val), false, false, val);
        }
        return AddressBookParser.quickCommand(next, val).execute(model);

    }

    public static Parser<StringCommand> parser() {
        return new Parser<StringCommand>() {
            @Override
            public StringCommand parse(String userInput) throws ParseException {
                ParserUtil.Pair p = ParserUtil.splitPipe(userInput);
                String val = p.first.trim();
                if (val.length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                return new StringCommand(val, p.second);
            }

        };
    }
}
