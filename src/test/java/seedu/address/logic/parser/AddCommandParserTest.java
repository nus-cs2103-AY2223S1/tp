package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplications.FACEBOOK;
import static seedu.address.testutil.TypicalApplications.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Contact;
import seedu.address.model.application.Date;
import seedu.address.model.application.Email;
import seedu.address.model.application.Position;
import seedu.address.testutil.ApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Application expectedApplication = new ApplicationBuilder(GOOGLE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE, new AddCommand(expectedApplication));

        // multiple companies - last company accepted
        assertParseSuccess(parser, COMPANY_DESC_FACEBOOK + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE, new AddCommand(expectedApplication));

        // multiple contacts - last contact accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_FACEBOOK + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE, new AddCommand(expectedApplication));

        // multiple dates - last date accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_FACEBOOK + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE, new AddCommand(expectedApplication));

        // multiple emails - last email accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + EMAIL_DESC_FACEBOOK + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE, new AddCommand(expectedApplication));

        // multiple positions - last position accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + EMAIL_DESC_GOOGLE + DATE_DESC_GOOGLE
                + POSITION_DESC_FACEBOOK + POSITION_DESC_GOOGLE, new AddCommand(expectedApplication));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags - used for later
        Application expectedApplication = new ApplicationBuilder(FACEBOOK).build();
        assertParseSuccess(parser, COMPANY_DESC_FACEBOOK + CONTACT_DESC_FACEBOOK + DATE_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + POSITION_DESC_FACEBOOK, new AddCommand(expectedApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser, VALID_COMPANY_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE, expectedMessage);

        // missing contact prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + VALID_CONTACT_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + VALID_DATE_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + VALID_EMAIL_GOOGLE
                + POSITION_DESC_GOOGLE, expectedMessage);

        // missing position prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + VALID_POSITION_GOOGLE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_GOOGLE + VALID_CONTACT_GOOGLE + VALID_DATE_GOOGLE + VALID_EMAIL_GOOGLE
                + VALID_POSITION_GOOGLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE, Company.MESSAGE_CONSTRAINTS);

        // invalid contact
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + INVALID_CONTACT_DESC + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE, Contact.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + INVALID_DATE_DESC + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE, Date.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + INVALID_EMAIL_DESC
                + POSITION_DESC_GOOGLE, Email.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_DESC + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + INVALID_EMAIL_DESC
                + POSITION_DESC_GOOGLE, Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
