package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveTaskCommand;

public class RemoveTaskCommandParserTest {
    private RemoveTaskCommandParser parser = new RemoveTaskCommandParser();

    @Test
    public void parse_validArgs_returnsTaskDeleteCommand() {
        assertParseSuccess(parser, "1", new RemoveTaskCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTaskCommand.MESSAGE_USAGE));
    }
}
