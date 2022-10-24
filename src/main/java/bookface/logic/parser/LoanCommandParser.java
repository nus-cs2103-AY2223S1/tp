package bookface.logic.parser;

import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.LoanCommand;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LoanCommand object
 */
public class LoanCommandParser implements Parser<LoanCommand> {

    //TODO Check if there's a better way to detect invalid loan commands
    public static final String VALIDATION_REGEX = "(\\d+\\s+\\d+\\s+(?s).*)|(\\d+\\s+\\d+)";

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

            if (nameKeywords.length < 3) {
                return new LoanCommand(userIndex, bookIndex);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE), pe);
        }

        try {
            String firstIndex = nameKeywords[0];
            String secondIndex = nameKeywords[1];
            Index userIndex = ParserUtil.parseIndex(firstIndex);
            Index bookIndex = ParserUtil.parseIndex(secondIndex);
            if (nameKeywords.length == 3) {
                String returnDate = nameKeywords[2];
                List<Date> parsedReturnDate = new PrettyTimeParser().parse(returnDate);
                if (parsedReturnDate.isEmpty()) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_PARSE));
                }
                return new LoanCommand(userIndex, bookIndex, parsedReturnDate.get(0));
            } else {
                StringBuilder stringbuilder = new StringBuilder();
                for (int i = 2; i < nameKeywords.length; i++) {
                    stringbuilder.append(nameKeywords[i]);
                    stringbuilder.append(" ");
                }
                String parsedString = stringbuilder.toString().trim();
                List<Date> parsedReturnDate = new PrettyTimeParser().parse(parsedString);
                if (parsedReturnDate.isEmpty()) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_PARSE));
                }
                return new LoanCommand(userIndex, bookIndex, parsedReturnDate.get(0));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_PARSE));
        }
    }
}

