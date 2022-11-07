package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.parser.predicates.IssueContainsKeywordsPredicate;
import seedu.address.model.Name;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Urgency;

/**
 * Represents the tests for parsing the FindIssueCommand.
 */
public class FindIssueCommandParserTest {
    private IssueCommandParser parser = new IssueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIssueCommand.MESSAGE_FIND_ISSUE_USAGE));
    }

    @Test
    public void parse_argsWithNoPrefix_throwsParseException() {
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, "abcd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
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
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG,
                " t/This is an issue title p/1 i/7 s/completed u/LOW n/DevEnable",
                expectedFindCommand);
    }

    @Test
    public void parse_validSinglePrefixWithNoRepetitionArgs_returnsFindCommand() {

        List<String> issueTitle = Arrays.asList("This is an issue title");
        List<String> projectName = Arrays.asList("DevEnable");
        List<String> urgency = Arrays.asList("LOW");
        List<String> status = Arrays.asList("completed");
        List<String> issueId = Arrays.asList("7");
        List<String> projectId = Arrays.asList("1");
        List<String> empty = new ArrayList<>();

        //title
        FindIssueCommand expectedFindCommandTitle =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(issueTitle, empty, empty, empty, empty, empty));
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG,
                " t/This is an issue title", expectedFindCommandTitle);

        //name
        FindIssueCommand expectedFindCommandName =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, empty, empty, projectName,
                        empty, empty));
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG, " n/DevEnable", expectedFindCommandName);

        //urgency
        FindIssueCommand expectedFindCommandUrgency =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, empty, urgency, empty,
                        empty, empty));
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG, " u/LOW", expectedFindCommandUrgency);

        //status
        FindIssueCommand expectedFindCommandStatus =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, status, empty, empty, empty, empty));
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG,
                " s/completed", expectedFindCommandStatus);

        //issue id
        FindIssueCommand expectedFindCommandIssueId =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, empty, empty, empty, empty, issueId));
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG, " i/7", expectedFindCommandIssueId);

        //project id
        FindIssueCommand expectedFindCommandProjectId =
                new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, empty, empty, empty, projectId, empty));
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG, " p/1", expectedFindCommandProjectId);

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
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG,
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
        assertParseSuccess(parser, FindIssueCommand.COMMAND_FLAG,
                " t/This is an issue title n/DevEnable p/1 s/completed u/NONE p/3 i/7 s/Incomplete"
                        + " u/HIGH i/8 u/MEDIUM n/AB3 t/another title",
                expectedFindCommand);
    }

    @Test
    public void parse_invalidMultiPrefix_throwsException() {
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " n/invalid@projectname", Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " p/0", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " p/-1", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " s/notCompletedOrIncomplete", Status.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " u/INVALID", Urgency.MESSAGE_STRING_CONSTRAINTS);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " i/0", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG,
                " i/-1", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyMultiPrefix_throwsException() {
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, " n/", Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, " p/", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, " s/", Status.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, " u/", Urgency.MESSAGE_STRING_CONSTRAINTS);
        assertParseFailure(parser, FindIssueCommand.COMMAND_FLAG, " i/", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
