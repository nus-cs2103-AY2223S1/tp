package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.SortIssueCommand;

class SortIssueCommandParserTest {

    private final IssueCommandParser parser = new IssueCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                SortIssueCommand.COMMAND_FLAG, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortIssueCommand.MESSAGE_USAGE));
        assertParseFailure(parser,
                SortIssueCommand.COMMAND_FLAG, "delete",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortIssueCommand.MESSAGE_USAGE));
    }
}
