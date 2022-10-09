package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        ListCommand expectedListPatientsCommand =
                new ListCommand("patients");
        assertParseSuccess(parser, "patients", expectedListPatientsCommand);

        ListCommand expectedListAppointmentsCommand =
                new ListCommand("appts");
        assertParseSuccess(parser, "appts", expectedListAppointmentsCommand);
    }

    @Test
    public void parse_invalidArgs_returnsListCommand() {
        assertParseFailure(parser, "wrong",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

}
