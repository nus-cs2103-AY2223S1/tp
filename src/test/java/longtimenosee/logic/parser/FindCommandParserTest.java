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
import longtimenosee.model.person.predicate.AddressContainsInputPredicate;
import longtimenosee.model.person.predicate.BirthdayMatchesInputPredicate;
import longtimenosee.model.person.predicate.EmailMatchesInputPredicate;
import longtimenosee.model.person.predicate.IncomeMatchesInputPredicate;
import longtimenosee.model.person.predicate.NameContainsKeywordsPredicate;
import longtimenosee.model.person.predicate.PhoneContainsNumberPredicate;
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
        // EP: empty input
        assertParseFailure(parser, " n/", Name.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: non-alphanumeric input: symbols
        assertParseFailure(parser, " n/!", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // EP: valid numeric input
        // 8 digit number
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PhoneContainsNumberPredicate("87654321")));
        assertParseSuccess(parser, " p/87654321", expectedFindCommand);

        // 10 digit number
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new PhoneContainsNumberPredicate("8765432112")));
        assertParseSuccess(parser, " p/8765432112", secondExpectedFindCommand);

        // 3 digit number
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new PhoneContainsNumberPredicate("123")));
        assertParseSuccess(parser, " p/123", thirdExpectedFindCommand); // Boundary value
    }

    @Test
    public void parse_invalidPhoneArgs_throwsParseException() {
        // EP: less than minimum number of digits: 2 digit number
        assertParseFailure(parser, " p/12", Phone.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: empty input
        assertParseFailure(parser, " p/", Phone.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: non-integer input
        assertParseFailure(parser, " p/1.1", Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " p/abc", Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // EP: alphabet input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new AddressContainsInputPredicate("Bedok")));
        assertParseSuccess(parser, " a/Bedok", expectedFindCommand);

        // EP: numeric input
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new AddressContainsInputPredicate("55")));
        assertParseSuccess(parser, " a/55", secondExpectedFindCommand);

        // EP: symbol input
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new AddressContainsInputPredicate("#01")));
        assertParseSuccess(parser, " a/#01", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidAddressArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " a/", Address.MESSAGE_CONSTRAINTS); // Boundary value
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        // EP: normal valid input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new EmailMatchesInputPredicate("Alice@example.com")));
        assertParseSuccess(parser, " e/Alice@example.com", expectedFindCommand);

        // EP: multiple domains
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new EmailMatchesInputPredicate("Bob@email.co.go")));
        assertParseSuccess(parser, " e/Bob@email.co.go", secondExpectedFindCommand);

        // EP: alphanumeric input
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new EmailMatchesInputPredicate("Carl_12@example1.co")));
        assertParseSuccess(parser, " e/Carl_12@example1.co", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidEmailArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " e/", Email.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: missing local part input
        assertParseFailure(parser, " e/@example.com", Email.MESSAGE_CONSTRAINTS);

        // EP: missing domain input
        assertParseFailure(parser, " e/alice@", Email.MESSAGE_CONSTRAINTS);

        // EP: end with period
        assertParseFailure(parser, " e/a@example.", Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // EP: single tag
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new TagContainsKeywordsPredicate(List.of("friends"))));
        assertParseSuccess(parser, " t/friends", expectedFindCommand);

        // EP: multiple tags
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new TagContainsKeywordsPredicate(List.of("friends", "family"))));
        assertParseSuccess(parser, " t/friends t/family", secondExpectedFindCommand);
    }

    @Test
    public void parse_invalidTagArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " t/", Tag.MESSAGE_CONSTRAINTS); // Boundary value
    }

    @Test
    public void parse_validBirthdayArgs_returnsFindCommand() {
        // EP: valid date input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new BirthdayMatchesInputPredicate("2019-05-12")));
        assertParseSuccess(parser, " b/2019-05-12", expectedFindCommand);
    }

    @Test
    public void parse_invalidBirthdayArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " b/", Birthday.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: numeric input
        assertParseFailure(parser, " b/123", Birthday.MESSAGE_FORMAT_CONSTRAINTS);

        // EP: alphabet input
        assertParseFailure(parser, " b/abc", Birthday.MESSAGE_FORMAT_CONSTRAINTS);

        // EP: incorrect date format
        assertParseFailure(parser, " b/1980-123-20", Birthday.MESSAGE_FORMAT_CONSTRAINTS);

        // EP: valid future date
        assertParseFailure(parser, " b/3000-12-12", Birthday.MESSAGE_DATE_CONSTRAINTS);
    }

    @Test
    public void parse_validIncomeArgs_returnsFindCommand() {
        // EP: valid numeric input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new IncomeMatchesInputPredicate("2000")));
        assertParseSuccess(parser, " i/2000", expectedFindCommand);
    }

    @Test
    public void parse_invalidIncomeArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " i/", Income.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: alphabet input
        assertParseFailure(parser, " i/abc", Income.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validRiskAppetiteArgs_returnsFindCommand() {
        // EP: high risk appetite
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new RiskAppetiteMatchesInputPredicate("H")));
        assertParseSuccess(parser, " ra/H", expectedFindCommand);

        // EP: medium risk appetite
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new RiskAppetiteMatchesInputPredicate("M")));
        assertParseSuccess(parser, " ra/M", secondExpectedFindCommand);

        // EP: low risk appetite
        FindCommand thirdExpectedFindCommand =
                new FindCommand(List.of(new RiskAppetiteMatchesInputPredicate("L")));
        assertParseSuccess(parser, " ra/L", thirdExpectedFindCommand);
    }

    @Test
    public void parse_invalidRiskAppetiteArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " ra/", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: invalid inputs, not one of the H,M,L options
        assertParseFailure(parser, " ra/abc", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " ra/HIGH", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " ra/High", RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validPolicyTitleArgs_returnsFindCommand() {
        // EP: valid title input
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PolicyTitleContainsKeywordsPredicate(Arrays.asList("Health", "Plan"))));
        assertParseSuccess(parser, " ti/Health Plan", expectedFindCommand);

        // EP: valid title input with whitespaces between keywords
        assertParseSuccess(parser, " ti/ Health  Plan  ", expectedFindCommand);

        // EP: valid alphanumeric input
        FindCommand alphanumericExpectedFindCommand =
                new FindCommand(List.of(new PolicyTitleContainsKeywordsPredicate(Arrays.asList("Life", "101"))));
        assertParseSuccess(parser, " ti/ Life 101 ", alphanumericExpectedFindCommand);
    }

    @Test
    public void parse_invalidPolicyTitleArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " ti/", Title.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: non-alphanumeric input: symbols
        assertParseFailure(parser, " ti/!", Title.MESSAGE_FORMAT_CONSTRAINTS);
    }

    @Test
    public void parse_validPolicyCoverageArgs_returnsFindCommand() {
        // EP: single coverage
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PolicyCoverageMatchesInputPredicate(List.of("LIFE"))));
        assertParseSuccess(parser, " cov/LIFE", expectedFindCommand);

        // EP: multiple coverages
        FindCommand secondExpectedFindCommand =
                new FindCommand(List.of(new PolicyCoverageMatchesInputPredicate(List.of("HEALTH", "TRAVEL"))));
        assertParseSuccess(parser, " cov/HEALTH cov/TRAVEL", secondExpectedFindCommand);
    }

    @Test
    public void parse_invalidPolicyCoverageArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " cov/", Coverage.MESSAGE_CONSTRAINTS); // Boundary value

        // EP: invalid input, not one of the valid coverage types
        assertParseFailure(parser, " cov/life", Coverage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " cov/L", Coverage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " cov/123", Coverage.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validPolicyCompanyArgs_returnsFindCommand() {
        // EP: valid input, one of the valid company options
        FindCommand expectedFindCommand =
                new FindCommand(List.of(new PolicyCompanyMatchesInputPredicate("AIA")));
        assertParseSuccess(parser, " cmp/AIA", expectedFindCommand);

        // EP: additional white spaces around the company option
        assertParseSuccess(parser, " cmp/ AIA", expectedFindCommand);
    }

    @Test
    public void parse_invalidPolicyCompanyArgs_throwsParseException() {
        // EP: empty input
        assertParseFailure(parser, " cmp/", Company.MESSAGE_FORMAT_CONSTRAINTS); // Boundary value

        // EP: invalid input, not one of the valid company options
        assertParseFailure(parser, " cmp/aia", Company.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " cmp/Aviva", Company.MESSAGE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, " cmp/123", Company.MESSAGE_FORMAT_CONSTRAINTS);
    }
}
