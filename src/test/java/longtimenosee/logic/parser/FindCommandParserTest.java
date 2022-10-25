package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.FindCommand;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Birthday;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Income;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Phone;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.person.predicate.AddressMatchesInputPredicate;
import longtimenosee.model.person.predicate.BirthdayMatchesInputPredicate;
import longtimenosee.model.person.predicate.EmailMatchesInputPredicate;
import longtimenosee.model.person.predicate.IncomeMatchesInputPredicate;
import longtimenosee.model.person.predicate.NameContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.PhoneMatchesNumberPredicate;
import longtimenosee.model.person.predicate.PolicyCompanyMatchesInputPredicate;
import longtimenosee.model.person.predicate.PolicyCoverageMatchesInputPredicate;
import longtimenosee.model.person.predicate.PolicyTitleContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.RiskAppetiteMatchesInputPredicate;
import longtimenosee.model.person.predicate.TagContainsKeywordsPredicate;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Title;
import longtimenosee.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ Alice  Bob  ", expectedFindCommand);

        // alphanumeric input
        FindCommand alphanumericExpectedFindCommand =
                new FindCommand(List.of(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "123"))));
        assertParseSuccess(parser, " n/ Alice 123 ", alphanumericExpectedFindCommand);
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        // empty name input
        assertParseFailure(parser, " n/", Name.MESSAGE_CONSTRAINTS);

        // symbol input
        assertParseFailure(parser, " n/!", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // 8 digit number
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PhoneMatchesNumberPredicate("87654321")));
        assertParseSuccess(parser, " p/87654321", expectedFindCommand);

        // 10 digit number
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new PhoneMatchesNumberPredicate("8765432112")));
        assertParseSuccess(parser, " p/8765432112", secondExpectedFindCommand);

        // 3 digit number
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new PhoneMatchesNumberPredicate("123")));
        assertParseSuccess(parser, " p/123", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidPhoneArgs_throwsParseException() {
        // 2 digit number
        assertParseFailure(parser, " p/12", Phone.MESSAGE_CONSTRAINTS);

        // empty input
        assertParseFailure(parser, " p/", Phone.MESSAGE_CONSTRAINTS);

        // non-integer input
        assertParseFailure(parser, " p/1.1", Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " p/abc", Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // normal word input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new AddressMatchesInputPredicate("Bedok")));
        assertParseSuccess(parser, " a/Bedok", expectedFindCommand);

        // number input
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new AddressMatchesInputPredicate("55")));
        assertParseSuccess(parser, " a/55", secondExpectedFindCommand);

        // symbol input
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new AddressMatchesInputPredicate("#01")));
        assertParseSuccess(parser, " a/#01", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidAddressArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " a/", Address.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        // normal input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new EmailMatchesInputPredicate("Alice@example.com")));
        assertParseSuccess(parser, " e/Alice@example.com", expectedFindCommand);

        // multiple domains
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new EmailMatchesInputPredicate("Bob@email.co.go")));
        assertParseSuccess(parser, " e/Bob@email.co.go", secondExpectedFindCommand);

        // alphanumeric input
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new EmailMatchesInputPredicate("Carl_12@example1.co")));
        assertParseSuccess(parser, " e/Carl_12@example1.co", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidEmailArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " e/", Email.MESSAGE_CONSTRAINTS);

        // missing local part input
        assertParseFailure(parser, " e/@example.com", Email.MESSAGE_CONSTRAINTS);

        // missing domain input
        assertParseFailure(parser, " e/alice@", Email.MESSAGE_CONSTRAINTS);

        // end with period
        assertParseFailure(parser, " e/a@example.", Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // one tag
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new TagContainsKeywordsPredicate(List.of("friends"))));
        assertParseSuccess(parser, " t/friends", expectedFindCommand);

        // multiple tags
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new TagContainsKeywordsPredicate(List.of("friends", "family"))));
        assertParseSuccess(parser, " t/friends t/family", secondExpectedFindCommand);
    }

    @Test
    public void parse_invalidTagArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " t/", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validBirthdayArgs_returnsFindCommand() {
        // normal input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new BirthdayMatchesInputPredicate("2019-05-12")));
        assertParseSuccess(parser, " b/2019-05-12", expectedFindCommand);
    }

    @Test
    public void parse_invalidBirthdayArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " b/", Birthday.MESSAGE_FORMAT_CONSTRAINTS);

        // incorrect format
        assertParseFailure(parser, " b/123", Birthday.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " b/abc", Birthday.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " b/1980-123-20", Birthday.MESSAGE_FORMAT_CONSTRAINTS);

        // future date
        assertParseFailure(parser, " b/3000-12-12", Birthday.MESSAGE_DATE_CONSTRAINTS);
    }

    @Test
    public void parse_validIncomeArgs_returnsFindCommand() {
        // normal input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new IncomeMatchesInputPredicate("2000")));
        assertParseSuccess(parser, " i/2000", expectedFindCommand);
    }

    @Test
    public void parse_invalidIncomeArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " i/", Income.MESSAGE_FORMAT_CONSTRAINTS);

        // alphabet input
        assertParseFailure(parser, " i/abc", Income.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validRiskAppetiteArgs_returnsFindCommand() {
        // high
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new RiskAppetiteMatchesInputPredicate("H")));
        assertParseSuccess(parser, " ra/H", expectedFindCommand);

        // medium
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new RiskAppetiteMatchesInputPredicate("M")));
        assertParseSuccess(parser, " ra/M", secondExpectedFindCommand);

        // low
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new RiskAppetiteMatchesInputPredicate("L")));
        assertParseSuccess(parser, " ra/L", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidRiskAppetiteArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " ra/", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);

        // invalid input
        assertParseFailure(parser, " ra/abc", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " ra/HIGH", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " ra/High", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validPolicyTitleArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PolicyTitleContainsKeywordsPredicate(Arrays.asList("Health", "Plan"))));
        assertParseSuccess(parser, " ti/Health Plan", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " ti/ Health  Plan  ", expectedFindCommand);

        // alphanumeric input
        FindCommand alphanumericExpectedFindCommand =
                new FindCommand(List.of(new PolicyTitleContainsKeywordsPredicate(Arrays.asList("Life", "101"))));
        assertParseSuccess(parser, " ti/ Life 101 ", alphanumericExpectedFindCommand);
    }

    @Test
    public void parse_invalidPolicyTitleArgs_throwsParseException() {
        // empty name input
        assertParseFailure(parser, " ti/", Title.MESSAGE_FORMAT_CONSTRAINTS);

        // symbol input
        assertParseFailure(parser, " ti/!", Title.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validPolicyCoverageArgs_returnsFindCommand() {
        // one tag
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PolicyCoverageMatchesInputPredicate(List.of("LIFE"))));
        assertParseSuccess(parser, " cov/LIFE", expectedFindCommand);

        // multiple tags
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new PolicyCoverageMatchesInputPredicate(List.of("HEALTH", "TRAVEL"))));
        assertParseSuccess(parser, " cov/HEALTH cov/TRAVEL", secondExpectedFindCommand);
    }

    @Test
    public void parse_invalidPolicyCoverageArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " cov/", Coverage.MESSAGE_CONSTRAINTS);

        // invalid input
        assertParseFailure(parser, " cov/life", Coverage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " cov/L", Coverage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " cov/123", Coverage.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validPolicyCompanyArgs_returnsFindCommand() {
        // valid input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PolicyCompanyMatchesInputPredicate("AIA")));
        assertParseSuccess(parser, " cmp/AIA", expectedFindCommand);

        // with white spaces
        assertParseSuccess(parser, " cmp/ AIA", expectedFindCommand);
    }

    @Test
    public void parse_invalidPolicyCompanyArgs_throwsParseException() {
        // empty input
        assertParseFailure(parser, " cmp/", Company.MESSAGE_FORMAT_CONSTRAINTS);

        // invalid input
        assertParseFailure(parser, " cmp/aia", Company.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " cmp/Aviva", Company.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " cmp/123", Company.MESSAGE_FORMAT_CONSTRAINTS);
    }
}
