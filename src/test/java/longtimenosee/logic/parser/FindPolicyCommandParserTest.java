package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.FindPolicyCommand;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Title;
import longtimenosee.model.policy.predicate.CompanyMatchesInputPredicate;
import longtimenosee.model.policy.predicate.CoverageMatchesInputPredicate;
import longtimenosee.model.policy.predicate.TitleContainsKeywordsPredicate;

public class FindPolicyCommandParserTest {
    private final FindPolicyCommandParser parser = new FindPolicyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // EP: empty arguments
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPolicyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTitleArgs_returnsFindCommand() {
        // EP: valid title input
        FindPolicyCommand expectedFindCommand =
                new FindPolicyCommand(List.of(new TitleContainsKeywordsPredicate(Arrays.asList("Health", "Plan"))));
        assertParseSuccess(parser, " ti/Health Plan", expectedFindCommand);

        // EP: valid title input with multiple whitespaces between keywords
        assertParseSuccess(parser, " ti/ Health  Plan  ", expectedFindCommand);

        // EP: alphanumeric input
        FindPolicyCommand alphanumericExpectedFindCommand =
                new FindPolicyCommand(List.of(new TitleContainsKeywordsPredicate(Arrays.asList("Life", "101"))));
        assertParseSuccess(parser, " ti/ Life 101 ", alphanumericExpectedFindCommand);
    }

    @Test
    public void parse_invalidTitleArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " ti/", Title.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: non-alphanumeric input: symbols
        assertParseFailure(parser, " ti/!", Title.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validCoverageArgs_returnsFindCommand() {
        // EP: single tag
        FindPolicyCommand expectedFindCommand =
                new FindPolicyCommand(List.of(new CoverageMatchesInputPredicate(List.of("LIFE"))));
        assertParseSuccess(parser, " cov/LIFE", expectedFindCommand);

        // EP: multiple tags
        FindPolicyCommand secondExpectedFindCommand =
                new FindPolicyCommand(List.of(new CoverageMatchesInputPredicate(List.of("HEALTH", "TRAVEL"))));
        assertParseSuccess(parser, " cov/HEALTH cov/TRAVEL", secondExpectedFindCommand);
    }

    @Test
    public void parse_invalidCoverageArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " cov/", Coverage.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: invalid input
        assertParseFailure(parser, " cov/life", Coverage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " cov/L", Coverage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " cov/123", Coverage.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validCompanyArgs_returnsFindCommand() {
        // EP: valid input
        FindPolicyCommand expectedFindCommand =
                new FindPolicyCommand(List.of(new CompanyMatchesInputPredicate("AIA")));
        assertParseSuccess(parser, " cmp/AIA", expectedFindCommand);

        // EP: valid input with additional white spaces
        assertParseSuccess(parser, " cmp/ AIA", expectedFindCommand);
    }

    @Test
    public void parse_invalidCompanyArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " cmp/", Company.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: invalid input
        assertParseFailure(parser, " cmp/aia", Company.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " cmp/Aviva", Company.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " cmp/123", Company.MESSAGE_FORMAT_CONSTRAINTS);
    }
}
