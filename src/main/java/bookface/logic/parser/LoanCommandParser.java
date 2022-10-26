package bookface.logic.parser;

import java.text.SimpleDateFormat;
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

        String firstIndex = nameKeywords[0];
        String secondIndex = nameKeywords[1];
        Index userIndex = ParserUtil.parseIndex(firstIndex);
        Index bookIndex = ParserUtil.parseIndex(secondIndex);

        if (nameKeywords.length < 3) {
            return new LoanCommand(userIndex, bookIndex);
        } else {
            try {
                StringBuilder stringbuilder = new StringBuilder();
                for (int i = 2; i < nameKeywords.length; i++) {
                    stringbuilder.append(nameKeywords[i]);
                    stringbuilder.append(" ");
                }
                String parsedString = stringbuilder.toString().trim();
                System.out.println(parsedString);
                // This is added to "override" prettytimeparser for some date formats as it is unable to parse formats
                // such as 26/10/2022 properly.
                if (parsedString.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date parsedDate = formatter.parse(parsedString);
                    return new LoanCommand(userIndex, bookIndex, parsedDate);
                } else {
                    List<Date> parsedReturnDate = new PrettyTimeParser().parse(parsedString);
                    System.out.println(parsedReturnDate);
                    if (parsedReturnDate.isEmpty()) {
                        throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_PARSE));
                    }
                    return new LoanCommand(userIndex, bookIndex, parsedReturnDate.get(0));
                }
            } catch (ParseException pe) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_PARSE));
            } catch (java.text.ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

