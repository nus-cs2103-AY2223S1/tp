package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.DeleteProjectCommand;

public class DeleteProjectCommandParserTest {

    private ProjectCommandParser parser = new ProjectCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser,
                DeleteProjectCommand.COMMAND_FLAG, "1", new DeleteProjectCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                DeleteProjectCommand.COMMAND_FLAG, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProjectCommand.MESSAGE_USAGE));
    }
}
