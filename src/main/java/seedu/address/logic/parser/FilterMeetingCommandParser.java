package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FilterMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingFilterDatePredicate;
import seedu.address.model.util.DateTimeConverter;

/**
 * Parses input arguments and creates a new FilterMeetingCommand object
 */
public class FilterMeetingCommandParser implements Parser<FilterMeetingCommand> {

    /**
     * Parses {@param args} into a command and returns it.
     *
     * @param args userInput's arguments
     * @return a new command
     * @throws ParseException if {@param args} does not conform the expected format
     */
    @Override
    public Command parse(String args) throws ParseException {
        LocalDateTime afterDate;
        LocalDateTime beforeDate;
        String trimmedArgs = args.trim();
        String[] nameKeywords = trimmedArgs.split(";;;");
        if (!trimmedArgs.contains(";;;") || nameKeywords.length == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.MESSAGE_USAGE));
        }
        try {
            afterDate = DateTimeConverter.stringToLocalDateTime(nameKeywords[0].trim());
            beforeDate = DateTimeConverter.stringToLocalDateTime(nameKeywords[1].trim());
        } catch (DateTimeParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.INVALID_DATE_FORMAT));
        }
        if (beforeDate.isBefore(afterDate)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.INVALID_DATE_POSITION));
        }
        return new FilterMeetingCommand(new MeetingFilterDatePredicate(afterDate, beforeDate));
    }
}
