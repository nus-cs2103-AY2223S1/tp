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
        LocalDateTime date1;
        LocalDateTime date2;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split(";;;");

        try {
            date1 = DateTimeConverter.stringToLocalDateTime(nameKeywords[0].trim());
            date2 = DateTimeConverter.stringToLocalDateTime(nameKeywords[1].trim());
        } catch (DateTimeParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.INVALID_DATE_FORMAT));
        }
        if (date2.isBefore(date1)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.INVALID_DATE_POSITION));
        }
        return new FilterMeetingCommand(new MeetingFilterDatePredicate(date1, date2));
    }
}
