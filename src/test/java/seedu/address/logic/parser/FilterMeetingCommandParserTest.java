package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterMeetingCommand;
import seedu.address.model.meeting.MeetingFilterDatePredicate;

public class FilterMeetingCommandParserTest {

    private FilterMeetingCommandParser parser = new FilterMeetingCommandParser();

    @Test
    public void parse_invalidParameters_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterMeetingCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "nonesense", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterMeetingCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a ;;; a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterMeetingCommand.INVALID_DATE_FORMAT));
        assertParseFailure(parser, " ;;; ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterMeetingCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "10-10-2022 1000 ;;; 09-10-2022 1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterMeetingCommand.INVALID_DATE_POSITION));
    }

    @Test
    public void parse_validArgs_returnsFilteredMeetingCommand() {
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 8, 10, 12);
        LocalDateTime date2 = LocalDateTime.of(2021, 2, 11, 1, 11);
        // no leading and trailing whitespaces
        FilterMeetingCommand expectedFilterMeetingCommand =
                new FilterMeetingCommand(new MeetingFilterDatePredicate(date1, date2));
        assertParseSuccess(parser, "08-01-2020 1012 ;;; 11-02-2021 0111", expectedFilterMeetingCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\t 08-01-2020 1012 \n ;;; 11-02-2021 0111", expectedFilterMeetingCommand);

        // same date
        FilterMeetingCommand expectedFilterMeetingCommandSameDate =
                new FilterMeetingCommand(new MeetingFilterDatePredicate(date1, date1));
        assertParseSuccess(parser, "\t 08-01-2020 1012 \n ;;; 08-01-2020 1012",
                expectedFilterMeetingCommandSameDate);
    }

}
