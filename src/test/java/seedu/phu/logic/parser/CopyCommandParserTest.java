package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.CopyCommand;

public class CopyCommandParserTest {

    private final String extraArgs = "1 2 3";
    private CopyCommandParser parser = new CopyCommandParser();

    @Test
    public void parse_validOneArg_returnsCopyCommand() {
        assertParseSuccess(parser, "1",
                new CopyCommand(INDEXES_FIRST_INTERNSHIP));
    }

    @Test
    public void parse_tooManyArgs_throwsParseException() {
        assertParseFailure(parser, extraArgs,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "zz",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
    }
}

