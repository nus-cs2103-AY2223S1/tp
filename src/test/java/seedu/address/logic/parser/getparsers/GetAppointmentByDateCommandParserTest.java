package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

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
}
