package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLIED_DATE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.APPLIED_DATE_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TIKTOK;
// import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
// import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
// import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
// import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
// import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.LINK_DESC_TIKTOK;
// import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalInternships.GOOGLE;
import static seedu.address.testutil.TypicalInternships.TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
// import seedu.address.model.internship.AppliedDate;
// import seedu.address.model.internship.Company;
// import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
// import seedu.address.model.internship.Link;
// import seedu.address.model.tag.Tag;
import seedu.address.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(TIKTOK).withTags(VALID_TAG_FRONTEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_GOOGLE + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_GOOGLE + DESCRIPTION_DESC_TIKTOK
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_GOOGLE
                + APPLIED_DATE_DESC_TIKTOK + TAG_DESC_FRONTEND, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags =
                new InternshipBuilder(TIKTOK).withTags(VALID_TAG_FRONTEND, VALID_TAG_BACKEND).build();
        assertParseSuccess(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK
                + DESCRIPTION_DESC_TIKTOK + APPLIED_DATE_DESC_TIKTOK
                + TAG_DESC_BACKEND + TAG_DESC_FRONTEND, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags().build();
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + LINK_DESC_GOOGLE + DESCRIPTION_DESC_GOOGLE
                        + APPLIED_DATE_DESC_GOOGLE,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser, VALID_COMPANY_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + APPLIED_DATE_DESC_TIKTOK,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + VALID_LINK_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + APPLIED_DATE_DESC_TIKTOK,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + VALID_DESCRIPTION_TIKTOK
                        + APPLIED_DATE_DESC_TIKTOK,
                expectedMessage);

        // missing applied date prefix
        assertParseFailure(parser, COMPANY_DESC_TIKTOK + LINK_DESC_TIKTOK + DESCRIPTION_DESC_TIKTOK
                        + VALID_APPLIED_DATE_TIKTOK,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_TIKTOK + VALID_LINK_TIKTOK + VALID_DESCRIPTION_TIKTOK
                        + VALID_APPLIED_DATE_TIKTOK,
                expectedMessage);
    }

    /*
    // Removed for now as there are no constraints on inputs
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Company.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_TIKTOK + INVALID_PHONE_DESC + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Link.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + INVALID_EMAIL_DESC + ADDRESS_DESC_TIKTOK
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Description.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, AppliedDate.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + ADDRESS_DESC_TIKTOK
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK + INVALID_ADDRESS_DESC,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_TIKTOK + PHONE_DESC_TIKTOK + EMAIL_DESC_TIKTOK
                + ADDRESS_DESC_TIKTOK + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }*/
}
