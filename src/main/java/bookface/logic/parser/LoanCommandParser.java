package bookface.logic.parser;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.LoanCommand;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LoanCommand object
 */
public class LoanCommandParser implements Parseable<LoanCommand> {

    //TODO Check if there's a better way to detect invalid loan commands
    public static final String VALIDATION_REGEX = "(\\d+\\s+\\d+)";

    /**
     * Parses the given {@code String} of arguments in the context of the LoanCommand
     * and returns a LoanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public LoanCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.matches(VALIDATION_REGEX)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");


        try {
            String firstIndex = nameKeywords[0];
            String secondIndex = nameKeywords[1];
            Index userIndex = ParserUtil.parseIndex(firstIndex);
            Index bookIndex = ParserUtil.parseIndex(secondIndex);
            return new LoanCommand(userIndex, bookIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE), pe);
        }
    }
}

