package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.COMPANY_DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.CONTACT_DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.CONTACT_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.DATE_DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.EMAIL_DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_FUTURE_DATE_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.application.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.application.logic.commands.CommandTestUtil.POSITION_DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.POSITION_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.application.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.application.logic.commands.CommandTestUtil.STATUS_DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.TAG_DESC_PREFERRED;
import static seedu.application.logic.commands.CommandTestUtil.TAG_DESC_TECH_COMPANY;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_CONTACT_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_PREFERRED;
import static seedu.application.logic.commands.CommandTestUtil.VALID_TAG_TECH_COMPANY;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalApplications.FACEBOOK;
import static seedu.application.testutil.TypicalApplications.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.AddCommand;
import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.application.Status;
import seedu.application.model.application.exceptions.InvalidFutureApplicationException;
import seedu.application.model.tag.Tag;
import seedu.application.testutil.ApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Application expectedApplication = new ApplicationBuilder(GOOGLE).withTags(VALID_TAG_TECH_COMPANY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple companies - last company accepted
        assertParseSuccess(parser, COMPANY_DESC_FACEBOOK + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple contacts - last contact accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_FACEBOOK + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple dates - last date accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_FACEBOOK + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple emails - last email accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + EMAIL_DESC_FACEBOOK + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple positions - last position accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + EMAIL_DESC_GOOGLE + DATE_DESC_GOOGLE
                + POSITION_DESC_FACEBOOK + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + EMAIL_DESC_GOOGLE + DATE_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + STATUS_DESC_FACEBOOK + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY,
                new AddCommand(expectedApplication));

        // multiple tags - all accepted
        Application expectedApplicationMultipleTags = new ApplicationBuilder(GOOGLE)
                .withTags(VALID_TAG_TECH_COMPANY, VALID_TAG_PREFERRED).build();
        assertParseSuccess(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + EMAIL_DESC_GOOGLE + DATE_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                new AddCommand(expectedApplicationMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Application expectedApplication = new ApplicationBuilder(FACEBOOK).withTags().build();
        assertParseSuccess(parser, COMPANY_DESC_FACEBOOK + CONTACT_DESC_FACEBOOK + DATE_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + POSITION_DESC_FACEBOOK + STATUS_DESC_FACEBOOK,
                new AddCommand(expectedApplication));
    }

    @Test
    public void parse_futureDateInput_failure() {
        String expectedMessage = new InvalidFutureApplicationException().getMessage();
        assertParseFailure(parser, COMPANY_DESC_FACEBOOK + CONTACT_DESC_FACEBOOK + INVALID_FUTURE_DATE_DESC
                + EMAIL_DESC_FACEBOOK + POSITION_DESC_FACEBOOK + STATUS_DESC_FACEBOOK, expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser, VALID_COMPANY_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE, expectedMessage);

        // missing contact prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + VALID_CONTACT_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + VALID_DATE_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + VALID_EMAIL_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE, expectedMessage);

        // missing position prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + VALID_POSITION_GOOGLE + STATUS_DESC_GOOGLE, expectedMessage);

        // missing status prefix
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + VALID_STATUS_GOOGLE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMPANY_GOOGLE + VALID_CONTACT_GOOGLE + VALID_DATE_GOOGLE
                + VALID_EMAIL_GOOGLE + VALID_POSITION_GOOGLE + VALID_STATUS_GOOGLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                Company.MESSAGE_CONSTRAINTS);

        // invalid contact
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + INVALID_CONTACT_DESC + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                Contact.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + INVALID_DATE_DESC + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                Date.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + INVALID_EMAIL_DESC
                + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                Email.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + INVALID_POSITION_DESC + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                Position.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + INVALID_STATUS_DESC + TAG_DESC_TECH_COMPANY + TAG_DESC_PREFERRED,
                Status.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE + EMAIL_DESC_GOOGLE
                + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + INVALID_TAG_DESC + VALID_TAG_TECH_COMPANY,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMPANY_DESC + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + INVALID_EMAIL_DESC + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE, Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                        + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE + TAG_DESC_TECH_COMPANY
                        + TAG_DESC_PREFERRED, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
