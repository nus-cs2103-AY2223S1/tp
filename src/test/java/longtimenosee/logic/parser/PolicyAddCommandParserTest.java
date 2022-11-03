package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.commands.CommandTestUtil.COMMISSION_DESC_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.COMMISSION_DESC_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.COMPANY_DESC_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.COMPANY_DESC_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.COVERAGE_DESC_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.COVERAGE_DESC_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_COMMISSION_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_COVERAGE_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static longtimenosee.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static longtimenosee.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static longtimenosee.logic.commands.CommandTestUtil.TITLE_DESC_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.TITLE_DESC_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMMISSION_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMPANY_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COVERAGE_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COVERAGE_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TITLE_MANUEXTRA;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;
import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static longtimenosee.testutil.TypicalPolicies.MANUEXTRA;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.PolicyAddCommand;
import longtimenosee.model.policy.Commission;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.Title;
import longtimenosee.testutil.PolicyBuilder;


public class PolicyAddCommandParserTest {
    private PolicyAddCommandParser parser = new PolicyAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Policy expectedPolicy = new PolicyBuilder(MANUEXTRA).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_MANUEXTRA + COMPANY_DESC_MANUEXTRA
                + COVERAGE_DESC_MANUEXTRA
                + COMMISSION_DESC_MANUEXTRA, new PolicyAddCommand(expectedPolicy));

        // multiple names - last name accepted
        assertParseSuccess(parser, TITLE_DESC_PRULIFE + TITLE_DESC_MANUEXTRA + COMPANY_DESC_MANUEXTRA
                + COVERAGE_DESC_MANUEXTRA
                + COMMISSION_DESC_MANUEXTRA, new PolicyAddCommand(expectedPolicy));

        // multiple companies - last company accepted
        assertParseSuccess(parser, TITLE_DESC_MANUEXTRA + COMPANY_DESC_PRULIFE + COMPANY_DESC_MANUEXTRA
                + COVERAGE_DESC_MANUEXTRA
                + COMMISSION_DESC_MANUEXTRA, new PolicyAddCommand(expectedPolicy));

        // multiple commissions - last commission accepted
        assertParseSuccess(parser, TITLE_DESC_MANUEXTRA + COMPANY_DESC_MANUEXTRA + COVERAGE_DESC_MANUEXTRA
                + COMMISSION_DESC_PRULIFE
                + COMMISSION_DESC_MANUEXTRA, new PolicyAddCommand(expectedPolicy));

        // multiple tags - all accepted
        Policy expectedPolicyMultipleTags = new PolicyBuilder(MANUEXTRA)
                .withCoverages(VALID_COVERAGE_PRULIFE, VALID_COVERAGE_MANUEXTRA)
                .build();
        assertParseSuccess(parser, TITLE_DESC_MANUEXTRA
                + COMPANY_DESC_MANUEXTRA
                + COVERAGE_DESC_MANUEXTRA
                + COVERAGE_DESC_PRULIFE
                + COMMISSION_DESC_MANUEXTRA, new PolicyAddCommand(expectedPolicyMultipleTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyAddCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_MANUEXTRA
                        + COMPANY_DESC_MANUEXTRA + COVERAGE_DESC_MANUEXTRA + COMMISSION_DESC_MANUEXTRA,
                expectedMessage);

        // missing company prefix
        assertParseFailure(parser, TITLE_DESC_MANUEXTRA
                        + VALID_COMPANY_MANUEXTRA + COVERAGE_DESC_MANUEXTRA + COMMISSION_DESC_MANUEXTRA,
                expectedMessage);

        // missing coverage prefix
        assertParseFailure(parser, TITLE_DESC_MANUEXTRA
                        + COMPANY_DESC_MANUEXTRA + VALID_COVERAGE_MANUEXTRA + COMMISSION_DESC_MANUEXTRA,
                expectedMessage);

        // missing commission prefix
        assertParseFailure(parser, TITLE_DESC_MANUEXTRA
                        + COMPANY_DESC_MANUEXTRA + COVERAGE_DESC_MANUEXTRA + VALID_COMMISSION_MANUEXTRA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_MANUEXTRA
                        + VALID_COMPANY_MANUEXTRA + VALID_COVERAGE_MANUEXTRA + VALID_COMMISSION_MANUEXTRA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + COMPANY_DESC_MANUEXTRA
                        + COVERAGE_DESC_MANUEXTRA + COMMISSION_DESC_MANUEXTRA,
                Title.MESSAGE_FORMAT_CONSTRAINTS);

        // invalid company
        assertParseFailure(parser, TITLE_DESC_MANUEXTRA + INVALID_COMPANY_DESC
                        + COVERAGE_DESC_MANUEXTRA + COMMISSION_DESC_MANUEXTRA,
                Company.MESSAGE_FORMAT_CONSTRAINTS);

        // invalid coverage
        assertParseFailure(parser, TITLE_DESC_MANUEXTRA + COMPANY_DESC_MANUEXTRA
                        + INVALID_COVERAGE_DESC + COMMISSION_DESC_MANUEXTRA,
                Coverage.MESSAGE_CONSTRAINTS);

        // invalid commission
        assertParseFailure(parser, TITLE_DESC_MANUEXTRA + COMPANY_DESC_MANUEXTRA
                        + COVERAGE_DESC_MANUEXTRA + INVALID_COMMISSION_DESC,
                Commission.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + COMPANY_DESC_MANUEXTRA + COVERAGE_DESC_MANUEXTRA
                        + INVALID_COMMISSION_DESC,
                Title.MESSAGE_FORMAT_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_MANUEXTRA + COMPANY_DESC_MANUEXTRA
                        + COVERAGE_DESC_MANUEXTRA
                        + COMMISSION_DESC_MANUEXTRA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyAddCommand.MESSAGE_USAGE));
    }
}
