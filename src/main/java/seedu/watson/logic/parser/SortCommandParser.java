package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Locale;

import seedu.watson.logic.commands.SortCommand;
import seedu.watson.logic.parser.exceptions.ParseException;

/**
 * Parses input argument and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    @Override
    public SortCommand parse(String userInput) throws ParseException {
        if (!userInput.contains(PREFIX_SUBJECT.toString())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        String userInputTrimmed = userInput.trim();
        String[] split = userInputTrimmed.split(PREFIX_SUBJECT.getPrefix());
        String subjectName = split[1].trim().toUpperCase(Locale.ROOT);
        String userRequest = split[0].trim().toUpperCase(Locale.ROOT);
        if (userRequest.equals(SortCommand.ASCENDING_ARGS)) {
            return new SortCommand(true, subjectName);
        } else if (userRequest.equals(SortCommand.DESCENDING_ARGS)) {
            return new SortCommand(false, subjectName);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
