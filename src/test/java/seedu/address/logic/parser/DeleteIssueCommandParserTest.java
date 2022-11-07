package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.model.Deadline;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_URGENCY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

public class DeleteIssueCommandParserTest {

    private IssueCommandParser parser = new IssueCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand(){
        assertParseSuccess(parser,
                DeleteIssueCommand.COMMAND_FLAG, "1", new DeleteIssueCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                DeleteIssueCommand.COMMAND_FLAG, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIssueCommand.MESSAGE_USAGE));
    }

}
