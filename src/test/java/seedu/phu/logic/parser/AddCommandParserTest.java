package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.commands.CommandTestUtil.APPLICATION_PROCESS_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.INTERNSHIP_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.INTERNSHIP_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_INTERNSHIP_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.phu.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_INTERNSHIP_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.WEBSITE_DESC_BOB;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.phu.testutil.TypicalInternships.AMY;
import static seedu.phu.testutil.TypicalInternships.BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.AddCommand;
import seedu.phu.model.internship.Date;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.tag.Tag;
import seedu.phu.testutil.InternshipBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INTERNSHIP_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INTERNSHIP_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INTERNSHIP_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + INTERNSHIP_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple internshipes - last internship accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INTERNSHIP_DESC_AMY
                + INTERNSHIP_DESC_BOB + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleTags = new InternshipBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INTERNSHIP_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB + APPLICATION_PROCESS_DESC_BOB
                + DATE_DESC_BOB + WEBSITE_DESC_BOB, new AddCommand(expectedInternshipMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Internship expectedInternship = new InternshipBuilder(AMY).withTags().withApplicationProcess("apply")
                .withDate(LocalDate.now().format(Date.DEFAULT_FORMATTER)).withWebsite("NA").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + INTERNSHIP_DESC_AMY + POSITION_DESC_AMY,
                new AddCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INTERNSHIP_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + INTERNSHIP_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + INTERNSHIP_DESC_BOB,
                expectedMessage);

        // missing internship prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_INTERNSHIP_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_INTERNSHIP_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INTERNSHIP_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + INTERNSHIP_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + INTERNSHIP_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INTERNSHIP_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + POSITION_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_INTERNSHIP_DESC + POSITION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INTERNSHIP_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + POSITION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
