package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Key;

public class GroupAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupAppointmentCommand.MESSAGE_USAGE);
    private GroupAppointmentCommandParser parser = new GroupAppointmentCommandParser();

    @Test
    public void parse_validArg_returnGroupAppointmentCommand() {
        assertParseSuccess(parser, " k/tag", new GroupAppointmentCommand(
                Key.TAG));
        assertParseSuccess(parser, " k/Tag", new GroupAppointmentCommand(
                Key.TAG));
        assertParseSuccess(parser, " k/taG", new GroupAppointmentCommand(
                Key.TAG));
        assertParseSuccess(parser, " k/patient", new GroupAppointmentCommand(
                Key.PATIENT));
    }

    @Test
    public void parse_invalidArg_fails() {
        assertParseFailure(parser, "k/ds", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "ear", MESSAGE_INVALID_FORMAT);
        assertThrows(ParseException.class, () -> new GroupAppointmentCommandParser().parse("leg"));
    }
}
