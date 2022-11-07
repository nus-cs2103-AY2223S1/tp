package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.commands.project.FindProjectCommand;
import seedu.address.logic.parser.predicates.IssueContainsKeywordsPredicate;
import seedu.address.logic.parser.predicates.ProjectContainsKeywordsPredicate;
import seedu.address.model.Name;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindIssueCommandParser {
    private IssueCommandParser parser = new IssueCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "-f", "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIssueCommand.MESSAGE_FIND_ISSUE_USAGE));
    }

    @Test
    public void parse_argsWithNoPrefix_throwsParseException() {
        assertParseFailure(parser, "-f", "abcd", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIssueCommand.MESSAGE_FIND_ISSUE_USAGE));
    }

    @Test
    public void parse_validMultiPrefixWithNoRepetitionArgs_returnsFindCommand() {

        List<String> issueTitle = Arrays.asList("This is an issue title");
        List<String> projectName = Arrays.asList("DevEnable");
        List<String> urgency = Arrays.asList("LOW");
        List<String> status = Arrays.asList("completed");
        List<String> issueId = Arrays.asList("7");
        List<String> projectId = Arrays.asList("1");



        FindIssueCommand expectedFindCommand =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(issueTitle, status, urgency, projectName,
                        projectId, issueId));
        assertParseSuccess(parser, "-f",
                " t/This is an issue title p/1 i/7 s/completed u/LOW n/DevEnable",
                expectedFindCommand);
    }

    @Test
    public void parse_validMultiPrefixWithRepetitionArgs_returnsFindCommand() {

        List<String> issueTitle = Arrays.asList("This is an issue title", "another title");
        List<String> projectName = Arrays.asList("DevEnable", "AB3");
        List<String> urgency = Arrays.asList("NONE", "HIGH", "MEDIUM");
        List<String> status = Arrays.asList("completed", "Incomplete");
        List<String> issueId = Arrays.asList("7", "8");
        List<String> projectId = Arrays.asList("1", "3");



        FindIssueCommand expectedFindCommand =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(issueTitle, status, urgency, projectName,
                        projectId, issueId));
        assertParseSuccess(parser, "-f",
                " t/This is an issue title t/another title p/1 p/3 i/7 i/8 s/completed s/Incomplete"
                + " u/NONE u/HIGH u/MEDIUM n/DevEnable n/AB3",
                expectedFindCommand);
    }

    @Test
    public void parse_validMultiPrefixInMixedOrder_returnsFindCommand() {

        List<String> issueTitle = Arrays.asList("This is an issue title", "another title");
        List<String> projectName = Arrays.asList("DevEnable", "AB3");
        List<String> urgency = Arrays.asList("NONE", "HIGH", "MEDIUM");
        List<String> status = Arrays.asList("completed", "Incomplete");
        List<String> issueId = Arrays.asList("7", "8");
        List<String> projectId = Arrays.asList("1", "3");



        FindIssueCommand expectedFindCommand =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(issueTitle, status, urgency, projectName,
                        projectId, issueId));
        assertParseSuccess(parser, "-f",
                " t/This is an issue title n/DevEnable p/1 s/completed u/NONE p/3 i/7 s/Incomplete"
                        + " u/HIGH i/8 u/MEDIUM n/AB3 t/another title",
                expectedFindCommand);
    }

    @Test
    public void parse_invalidMultiPrefix_throwsException() {
        assertParseFailure(parser, "-f", " n/invalid@projectname", Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "-f", " p/0", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-f", " p/-1", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-f", " s/notCompletedOrIncomplete", Status.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "-f", " u/INVALID", Urgency.MESSAGE_STRING_CONSTRAINTS);
        assertParseFailure(parser, "-f", " i/0", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-f", " i/-1", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
