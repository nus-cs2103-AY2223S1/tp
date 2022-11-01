package hobbylist.logic.parser;

import hobbylist.logic.commands.RateAboveCommand;
import hobbylist.logic.parser.exceptions.ParseException;

/**
 * Parse the input arg.
 */
public class RateAboveCommandParser implements Parser<RateAboveCommand> {
    public static final String INPUT_EMPTY = "Input value cannot be empty!";
    public static final String INPUT_FORMAT_WRONG = "Input format should be r/above value!\n"
                    + "The input value should be an integer between 1-5 (both inclusive).";
    public static final String INPUT_OUT_OF_BOUND = "Input value out of bound!\n" +
            "The input value should be an integer between 1-5 (both inclusive).";

    /**
     * Parses the given {@code String} of arguments in the context of the RateAboveCommand
     * and returns a RateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format or rate value
     *     is out of bound.
     */
    public RateAboveCommand parse(String args) throws ParseException {
        int i = 0;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(INPUT_EMPTY);
        }
        try {
            i = Integer.parseInt(trimmedArgs);
        } catch (Exception e) {
            throw new ParseException(
                    INPUT_FORMAT_WRONG);
        }
        if (i < 1 || i > 5) {
            throw new ParseException(INPUT_OUT_OF_BOUND);
        }
        return new RateAboveCommand(i);
    }
}
