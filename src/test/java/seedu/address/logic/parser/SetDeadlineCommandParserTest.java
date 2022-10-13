package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetDeadlineCommand;

class SetDeadlineCommandParserTest {
    private SetDeadlineCommandParser parser = new SetDeadlineCommandParser();

    @Test
    public void parse_validArgs_returnsSetDeadlineCommand() {
        assertParseSuccess(parser, " 1 2022-12-25 23:59", new SetDeadlineCommand(0,
                LocalDateTime.parse("2022-12-25 23:59")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetDeadlineCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertParseFailure(parser, "1 2022-22-22", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetDeadlineCommand.MESSAGE_USAGE));
    }

}
