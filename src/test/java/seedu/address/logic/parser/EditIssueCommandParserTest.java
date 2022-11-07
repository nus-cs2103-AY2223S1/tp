package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_URGENCY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.URGENCY_DESC_URGENCY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;

class EditIssueCommandParserTest {

    private IssueCommandParser parser = new IssueCommandParser();
    Project projectStub = new Project(new Name("Stub"));
    Issue issueStub = new Issue(new Title("Stub"), new Deadline("2020-12-10"), Urgency.NONE,
            new Status(false), projectStub, new IssueId(1), new Pin(false));
    IssueId stubId = issueStub.getIssueId();
    String DESC_ISSUE = " " + PREFIX_ISSUE_ID + Integer.toString(stubId.getIdInt());

    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {

        assertParseSuccess(parser, EditIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + DESC_ISSUE
                + DEADLINE_DESC_DEADLINE + URGENCY_DESC_URGENCY,
                new EditIssueCommand(new Title(VALID_TITLE), new Deadline(VALID_DEADLINE), Urgency.LOW, stubId));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE);

        //no issue id
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + DEADLINE_DESC_DEADLINE,
                expectedMessage);
    }

    @Test
    public void parse_allOptionalFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_MISSING_ARGUMENTS, EditIssueCommand.MESSAGE_USAGE);

        //no optional field
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, DESC_ISSUE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid title
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, INVALID_TITLE_DESC + DESC_ISSUE
                + DEADLINE_DESC_DEADLINE, Title.MESSAGE_CONSTRAINTS);

        //invalid deadline
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + DESC_ISSUE
                + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        //invalid urgency
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + DESC_ISSUE
                + INVALID_URGENCY_DESC, Urgency.MESSAGE_CONSTRAINTS);
    }

}
