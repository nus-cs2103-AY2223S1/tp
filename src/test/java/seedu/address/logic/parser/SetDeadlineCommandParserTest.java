package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.SetDeadlineCommand;

import java.time.LocalDate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class SetDeadlineCommandParserTest {
    private SetDeadlineCommandParser parser = new SetDeadlineCommandParser();

    @Test
    public void parse_validArgs_returnsSetDeadlineCommand() {
        assertParseSuccess(parser, " 1 2022-12-25", new SetDeadlineCommand(0, LocalDate.parse("2022-12-25")));
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