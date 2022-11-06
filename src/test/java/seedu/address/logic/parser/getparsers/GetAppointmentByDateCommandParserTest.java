package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetAppointmentByDateCommand;
import seedu.address.model.person.AppointmentByDatePredicate;

public class GetAppointmentByDateCommandParserTest {
    private GetAppointmentByDateCommandParser parser = new GetAppointmentByDateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetAppointmentByDateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_garbledArg_returnsGetAppointmentByDateCommand() {
        // extra prefixes
        String userInput = "/inp /outp";
        assertThrows(DateTimeParseException.class, () -> prepareCommand(userInput));

        // garbled input
        String garbledInput = "abcdef123g";
        assertThrows(DateTimeParseException.class, () -> prepareCommand(garbledInput));
    }

    @Test
    public void parse_invalidDate_exceptionThrown() {
        // invalid date
        String invalidDateValue = "40-20-2010";
        assertThrows(DateTimeParseException.class, () -> prepareCommand(invalidDateValue));

        // invalid date format
        String invalidDateFormat = "30-1-2020";
        assertThrows(DateTimeParseException.class, () -> prepareCommand(invalidDateFormat));
    }

    @Test
    public void parse_validArgs_returnsGetAppointmentByDateCommand() {
        // no leading and trailing whitespaces
        GetAppointmentByDateCommand expectedGetAppointmentByDateCommand =
                new GetAppointmentByDateCommand(
                        new AppointmentByDatePredicate(Arrays.asList(LocalDate.of(2000, Month.APRIL, 12))));
        assertParseSuccess(parser, "12-04-2000", expectedGetAppointmentByDateCommand);

        // allows multiple inputs
        GetAppointmentByDateCommand multipleGetAppointmentByDateCommand =
                new GetAppointmentByDateCommand(new AppointmentByDatePredicate(
                        Arrays.asList(LocalDate.of(
                                2000, Month.APRIL, 12), LocalDate.of(2013, Month.APRIL, 13))));
        assertParseSuccess(parser, "12-04-2000 13-04-2013", multipleGetAppointmentByDateCommand);
    }

    private GetAppointmentByDateCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<LocalDate> dates = new ArrayList<>();
        for (String i : st) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(i.trim(), dateTimeFormatter);
            dates.add(date);
        }
        return new GetAppointmentByDateCommand(new AppointmentByDatePredicate(dates));
    }
}
