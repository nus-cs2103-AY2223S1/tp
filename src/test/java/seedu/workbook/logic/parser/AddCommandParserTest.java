package seedu.workbook.logic.parser;

import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.workbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.workbook.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.workbook.testutil.TypicalInternships.AMY;
import static seedu.workbook.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.workbook.logic.commands.AddCommand;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Phone;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.tag.Tag;
import seedu.workbook.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                         + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship));

        // multiple company names - last company name accepted
        assertParseSuccess(parser, COMPANY_DESC_AMY + COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple roles - last role accepted
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_AMY + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedInternship));


        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
                COMPANY_DESC_AMY + ROLE_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser,
                VALID_COMPANY_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing company prefix
        assertParseFailure(parser,
                COMPANY_DESC_BOB + VALID_ROLE_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser,
                VALID_COMPANY_BOB + VALID_ROLE_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser,
                INVALID_COMPANY_DESC + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Company.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser,
                COMPANY_DESC_BOB + INVALID_ROLE_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Role.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);



        // invalid tag
        assertParseFailure(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_COMPANY_DESC + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + COMPANY_DESC_BOB + ROLE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
