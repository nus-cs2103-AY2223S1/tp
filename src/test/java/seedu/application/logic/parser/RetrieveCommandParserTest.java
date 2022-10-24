package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.RetrieveCommand;

public class RetrieveCommandParserTest {

    private RetrieveCommandParser parser = new RetrieveCommandParser();

    @Test
    public void parse_validArgs_returnsRetrieveCommand() {
        assertParseSuccess(parser, "1", new RetrieveCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RetrieveCommand.MESSAGE_USAGE));
    }
}
