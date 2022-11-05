package coydir.logic.parser;

import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import coydir.commons.core.Messages;
import coydir.logic.commands.DeleteLeaveCommand;
import coydir.model.person.EmployeeId;

public class DeleteLeaveCommandParserTest {

    private DeleteLeaveCommandParser parser = new DeleteLeaveCommandParser();
    private String invalidFormat = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
        DeleteLeaveCommand.MESSAGE_USAGE);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteLeaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertParseFailure(parser, "1", invalidFormat);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        DeleteLeaveCommand expectedAddLeaveCommand = new DeleteLeaveCommand(new EmployeeId("1"), 1);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " id/1 i/1", expectedAddLeaveCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n id/1 \n i/1 \t", expectedAddLeaveCommand);
    }

}
