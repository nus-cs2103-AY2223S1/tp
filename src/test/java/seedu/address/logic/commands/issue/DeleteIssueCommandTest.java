package seedu.address.logic.commands.issue;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.IssueCommandParser;

class DeleteIssueCommandTest {

    private final IssueCommandParser parser = new IssueCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser,
                DeleteIssueCommand.COMMAND_FLAG, "1", new DeleteIssueCommand(INDEX_FIRST));
        assertParseSuccess(parser,
                DeleteIssueCommand.COMMAND_FLAG, "2", new DeleteIssueCommand(INDEX_SECOND));
        assertParseSuccess(parser,
                DeleteIssueCommand.COMMAND_FLAG, "3", new DeleteIssueCommand(INDEX_THIRD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                DeleteIssueCommand.COMMAND_FLAG, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIssueCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                DeleteIssueCommand.COMMAND_FLAG, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIssueCommand.MESSAGE_USAGE));
    }
}