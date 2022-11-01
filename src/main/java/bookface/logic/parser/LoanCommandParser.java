package bookface.logic.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
public class LoanCommandParser implements Parseable<LoanCommand> {

    //TODO Check if there's a better way to detect invalid loan commands
    public static final String VALIDATION_REGEX = "(\\d+\\s+\\d+\\s+(?s).*)|(\\d+\\s+\\d+)";

    /**
     * Parses the given {@code String} of arguments in the context of the LoanCommand
     * and returns a LoanCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public LoanCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.matches(VALIDATION_REGEX)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        String firstIndex = nameKeywords[0];
        String secondIndex = nameKeywords[1];
        Index userIndex;
        Index bookIndex;
        try {
            userIndex = ParserUtil.parseIndex(firstIndex);
            bookIndex = ParserUtil.parseIndex(secondIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LoanCommand.MESSAGE_USAGE), pe);
        }

        if (nameKeywords.length < 3) {
            return new LoanCommand(userIndex, bookIndex);
        } else {
            StringBuilder stringbuilder = new StringBuilder();
            for (int i = 2; i < nameKeywords.length; i++) {
                stringbuilder.append(nameKeywords[i]);
                stringbuilder.append(" ");
            }
            String parsedString = stringbuilder.toString().trim();
            // The first two if cases are added to "override" prettytimeparser for some date formats as it is
            // unable to parse formats such as 26/10/2022 and handle invalid date cases properly.
            // Edited from https://stackoverflow.com/questions/62054264/check-invalid-date-by-localdate
            if (parsedString.matches("^([0-9][0-9])/([0-9][0-9])/([0-9][0-9])?[0-9][0-9]$")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                formatter = formatter.withResolverStyle(ResolverStyle.SMART);
                try {
                    LocalDate ld = LocalDate.parse(parsedString, formatter);
                    Date date = java.sql.Date.valueOf(ld);
                    return new LoanCommand(userIndex, bookIndex, date);
                } catch (DateTimeParseException e) {
                    throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
                }
            } else if (parsedString.matches("^([0-9][0-9])?[0-9][0-9]-([0-9][0-9])-([0-9][0-9])$")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                formatter = formatter.withResolverStyle(ResolverStyle.SMART);
                try {
                    LocalDate ld = LocalDate.parse(parsedString, formatter);
                    Date date = java.sql.Date.valueOf(ld);
                    return new LoanCommand(userIndex, bookIndex, date);
                } catch (DateTimeParseException e) {
                    throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
                }
            } else {
                List<Date> parsedReturnDate = new PrettyTimeParser().parse(parsedString);
                if (parsedReturnDate.isEmpty()) {
                    throw new ParseException(String.format(Messages.MESSAGE_INVALID_DATE_PARSE, LoanCommand.MESSAGE_USAGE));
                }
                return new LoanCommand(userIndex, bookIndex, parsedReturnDate.get(0));
            }
        }
    }
}

