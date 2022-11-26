package seedu.workbook.logic.parser;

import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.COMPANY_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.DATETIME_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.INVALID_STAGE_DESC;
import static seedu.workbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.workbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.workbook.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.STAGE_DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.STAGE_DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_STAGE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.workbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.workbook.testutil.TypicalInternships.AMY;
import static seedu.workbook.testutil.TypicalInternships.BOB;

import org.junit.jupiter.api.Test;

import seedu.workbook.logic.commands.AddCommand;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.DateTime;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;
import seedu.workbook.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_BOB
                         + STAGE_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship));

        // multiple company names - last company name accepted
        assertParseSuccess(parser, COMPANY_DESC_AMY + COMPANY_DESC_BOB + ROLE_DESC_BOB
                + EMAIL_DESC_BOB + STAGE_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship));

        // multiple roles - last role accepted
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_AMY + ROLE_DESC_BOB + EMAIL_DESC_BOB
               + STAGE_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));


        // multiple emails - last email accepted
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + STAGE_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedInternship));

        // multiple stages - last stage accepted
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_BOB
                + STAGE_DESC_AMY + STAGE_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_BOB
                + STAGE_DESC_BOB + DATETIME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Internship expectedInternship = new InternshipBuilder(AMY).withDateTime("").withEmail("").withTags().build();
        assertParseSuccess(parser,
                COMPANY_DESC_AMY + ROLE_DESC_AMY + STAGE_DESC_AMY,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing company prefix
        assertParseFailure(parser,
                VALID_COMPANY_BOB + ROLE_DESC_BOB + STAGE_DESC_BOB,
                expectedMessage);

        // missing role prefix
        assertParseFailure(parser,
                COMPANY_DESC_BOB + VALID_ROLE_BOB + STAGE_DESC_BOB,
                expectedMessage);


        // missing stage prefix
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + VALID_STAGE_BOB,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser,
                VALID_COMPANY_BOB + VALID_ROLE_BOB + VALID_STAGE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company
        assertParseFailure(parser,
                INVALID_COMPANY_DESC + ROLE_DESC_BOB + EMAIL_DESC_BOB + STAGE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Company.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser,
                COMPANY_DESC_BOB + INVALID_ROLE_DESC + EMAIL_DESC_BOB + STAGE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Role.MESSAGE_CONSTRAINTS);


        // invalid email
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + INVALID_EMAIL_DESC + STAGE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid stage
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_BOB + INVALID_STAGE_DESC
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Stage.MESSAGE_CONSTRAINTS);

        // invalid DateTime
        assertParseFailure(parser,
                COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_BOB + STAGE_DESC_BOB
                        + INVALID_DATETIME_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                DateTime.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                INVALID_COMPANY_DESC + ROLE_DESC_BOB + EMAIL_DESC_BOB + STAGE_DESC_BOB,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + COMPANY_DESC_BOB + ROLE_DESC_BOB + EMAIL_DESC_BOB
                        + STAGE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
