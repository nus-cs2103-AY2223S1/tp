package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.HelpStuCommand;

public class HelpStuCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpStuCommand.MESSAGE_USAGE);

    private HelpStuCommandParser parser = new HelpStuCommandParser();

    @Test
    public void parse_validIndex_returnsAttendanceCommand() {
        assertParseSuccess(parser, "1", new HelpStuCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // string as index
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);

        // blank space as index
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // 0 as index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        //negative value as index
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }
}
