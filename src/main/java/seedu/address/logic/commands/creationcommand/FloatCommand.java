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
 * Command to allow the user to create a float number
 */
public class FloatCommand extends PureCommand {
    public static final String COMMAND_WORD = "float";
    private static final String INVALID_INPUT = "The input is not an float";

    private Float num;
    private String next;

    /**
     * Constructor to create a new float
     *
     * @param num
     * @param next
     */
    public FloatCommand(Float num, String next) {
        this.num = num;
        this.next = next;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (next == null || next.equals("")) {
            return new CommandResult(String.format("created %f", num), false, false, num);
        }
        return AddressBookParser.quickCommand(next, num).execute(model);
    }

    /**
     * Returns a parser to parse user input for Float command
     */
    public static Parser<FloatCommand> parser() {
        return new Parser<FloatCommand>() {
            @Override
            public FloatCommand parse(String userInput) throws ParseException {
                ParserUtil.Pair p = ParserUtil.splitPipe(userInput);

                if (p.getFirst().length() == 0) {
                    throw new ParseException(INVALID_INPUT);
                }
                Float num;
                try {
                    num = Float.parseFloat(p.getFirst());
                } catch (NumberFormatException e) {
                    throw new ParseException(INVALID_INPUT);
                }
                return new FloatCommand(num, p.getSecond());
            }
        };
    }
}
