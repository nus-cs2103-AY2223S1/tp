package seedu.address.logic.commands.creationcommand;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Command to allow the user to create an integer
 */
public class IntCommand extends PureCommand {

    public static final String COMMAND_WORD = "int";
    private static final String INVALID_INPUT = "The input is not an integer";

    private Integer num;
    private String next;

    /**
     * Constructor to create a int command
     */
    public IntCommand(Integer num, String next) {
        this.num = num;
        this.next = next;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (next == null || next.equals("")) {
            return new CommandResult(String.format("created %d", num), false, false, num);
        }
        return AddressBookParser.quickCommand(next, num).execute(model);
    }

    /**
     * Returns a parser to parse user input for int command
     */
    public static Parser<IntCommand> parser() {
        return new Parser<IntCommand>() {
            @Override
            public IntCommand parse(String userInput) throws ParseException {
                userInput = userInput.trim();
                ParserUtil.Pair p = ParserUtil.splitPipe(userInput);
                if (p.getFirst().length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                Integer num;
                try {
                    num = Integer.parseInt(p.getFirst());
                } catch (NumberFormatException e) {
                    throw new ParseException(INVALID_INPUT);
                }
                return new IntCommand(num, p.getSecond());
            }
        };
    }
}
