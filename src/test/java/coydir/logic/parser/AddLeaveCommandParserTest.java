package coydir.logic.parser;

import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import coydir.commons.core.Messages;
import coydir.logic.commands.AddLeaveCommand;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;

public class AddLeaveCommandParserTest {

    private AddLeaveCommandParser parser = new AddLeaveCommandParser();
    private String invalidFormat = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
        AddLeaveCommand.MESSAGE_USAGE);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddLeaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing date
        assertParseFailure(parser, "1" + "01-01-2022", invalidFormat);

        // missing employee id
        assertParseFailure(parser, "01-01-2022" + "01-01-2022", invalidFormat);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        AddLeaveCommand expectedAddLeaveCommand = new AddLeaveCommand(new EmployeeId("1"),
                new Leave("01-01-2022", "01-01-2022"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " id/1 sd/01-01-2022 ed/01-01-2022", expectedAddLeaveCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n id/1 \n sd/01-01-2022 \t ed/01-01-2022  \t", expectedAddLeaveCommand);
    }

}
