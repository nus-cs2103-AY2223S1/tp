package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.SortProjectCommand;

class SortProjectCommandParserTest {

    private final ProjectCommandParser parser = new ProjectCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                SortProjectCommand.COMMAND_FLAG, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortProjectCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                SortProjectCommand.COMMAND_FLAG, "delete",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortProjectCommand.MESSAGE_USAGE));
    }
}
