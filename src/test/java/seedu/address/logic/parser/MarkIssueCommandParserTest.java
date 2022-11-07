package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.issue.MarkIssueCommand;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

class MarkIssueCommandParserTest {

    private IssueCommandParser parser = new IssueCommandParser();
    Project projectStub = new Project(new Name("Stub"));

    Issue issueStub = new Issue(new Title("Stub"), new Deadline("2020-12-10"), Urgency.NONE,
            new Status(false), projectStub, new IssueId(1), new Pin(false));
    IssueId stubId = issueStub.getIssueId();
    String DESC_ISSUE = " " + PREFIX_ISSUE_ID + Integer.toString(stubId.getIdInt());

    @Test
    public void parse_ValidArgs_returnsMarkIssueCommand() {
        Status status = new Status(true);
        assertParseSuccess(parser, MarkIssueCommand.COMMAND_FLAG, "1", new MarkIssueCommand(status, new IssueId(1)));

    }


}
