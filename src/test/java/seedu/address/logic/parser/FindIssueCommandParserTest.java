package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.parser.predicates.IssueContainsKeywordsPredicate;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.URGENCY_DESC_URGENCY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;

class FindIssueCommandParserTest {

    private IssueCommandParser parser = new IssueCommandParser();
    Project projectStub = new Project(new Name("Stub"));

    Issue issueStub = new Issue(new Title("Stub"), new Deadline("2020-12-10"), Urgency.NONE,
            new Status(false), projectStub, new IssueId(1), new Pin(false));
    IssueId stubId = issueStub.getIssueId();
    String DESC_ISSUE = " " + PREFIX_ISSUE_ID + Integer.toString(stubId.getIdInt());

    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {

        ArrayList<String> title = new ArrayList<String>();
        title.add("Fix errors");
        IssueContainsKeywordsPredicate predicate = new IssueContainsKeywordsPredicate(title, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE, new FindIssueCommand(predicate));

    }
}

